package ir.infra.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.extras.codecs.joda.InstantCodec;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import ir.infra.core.ClusterConf;
import ir.infra.core.Constants;
import ir.infra.tables.EmsInfo;

import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.*;

import static com.datastax.driver.core.querybuilder.QueryBuilder.gt;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.token;

/**
 * Cassandra client to insert objects into the cassandra cluster.
 * The configured keyspace is {@link Constants#KEY_SPACE} and all objects are inserted using
 * {@link MappingManager} class utility.
 */
public class CassandraClient {
    public static final String ID = "emsinfoid";
    public static final String ALLOWED = "allowed";
    public static final String WT = "WT";

    private final Session session;
    Mapper<EmsInfo> emsInfoMapper;
    Cluster cluster;
    private ClusterConf conf;
    ExecutorService executorService;

    public CassandraClient(ClusterConf conf) throws UnknownHostException {
        cluster = Cluster.builder()
                .addContactPoints(conf.getCoordinator())
                .withReconnectionPolicy(new ConstantReconnectionPolicy(10000))
                .build();
        this.conf = conf;

        cluster.getConfiguration().getCodecRegistry()
                .register(InstantCodec.instance);

        cluster.getConfiguration().getQueryOptions().setFetchSize(100);

        cluster.init();

        if (conf.isEnable_test()) {
            System.out.println("Connecting to keyspace: " + Constants.TEST_KEY_SPACE);
            session = cluster.connect(Constants.TEST_KEY_SPACE);
        }
        else {
            System.out.println("Connecting to keyspace: " + Constants.KEY_SPACE);
            session = cluster.connect(Constants.KEY_SPACE);
        }

        MappingManager mappingManager = new MappingManager(session);
        emsInfoMapper = mappingManager.mapper(EmsInfo.class);

        executorService = Executors.newFixedThreadPool(cluster.getMetadata().getAllHosts().size() * conf.getThreadPerHost());
    }

    public boolean isConnected() {
        return !session.isClosed();
    }

    public void add(EmsInfo emsInfo) {
        emsInfoMapper.save(emsInfo);
    }

    public EmsInfo getEmsInfo(long id) {
        return emsInfoMapper.get(id);
    }

    public void deleteOldAllowed() {
        long ts = (System.currentTimeMillis() - conf.getOld_allowed_sec() * 1000) * 1000;
        Select select = QueryBuilder.select()
                .column(ID).column(ALLOWED)
                .writeTime(ALLOWED).as(WT)
                .from(Constants.KEY_SPACE, Constants.TABLE);

        int numDeletes = 0;
        PreparedStatement prepare = session.prepare("DELETE FROM traffic.emsinfo" +
                " WHERE emsinfoid = ? ");
        BatchStatement batchStatement = new BatchStatement();

        ResultSet rows = session.execute(select);
        for (Row row : rows) {
            Long id = row.get(ID, Long.class);
            long ws = row.get(WT, Long.class);
            boolean allowed = row.get(ALLOWED, Boolean.class);

            if (!allowed || ws > ts)
                continue;

            BoundStatement statement = prepare.bind(id);
            batchStatement.add(statement);
            numDeletes = numDeletes + 1;

            if (numDeletes % 100 == 0) {
                session.executeAsync(batchStatement);
                batchStatement.clear();
            }
            if (numDeletes % 1000 == 0)
                System.out.println("Num sent deletes: " + numDeletes);
        }

        session.executeAsync(batchStatement);

        System.out.println("All deletes sends, num deletes: " + numDeletes);
    }

    public void deleteOldAllowedParallel() throws ExecutionException, InterruptedException, UnknownHostException {

        long tsMiS = (System.currentTimeMillis() - conf.getOld_allowed_sec() * 1000) * 1000;

        Metadata metadata = cluster.getMetadata();

        Set<Host> allHosts = cluster.getMetadata().getAllHosts();

        List<Future<TokenRangeDeletes>> futures = new ArrayList<>();

        Set<TokenRange> allTokenRanges = metadata.getTokenRanges();

        Map<Host, List<TokenRange>> hostTokenRangeMap = new HashMap<>();
        for(Host host: allHosts)
            hostTokenRangeMap.put(host, new ArrayList<>());

        for (Host host : allHosts) {
            for (Token token : host.getTokens()) {
                for (TokenRange tokenRange : allTokenRanges) {
                    if (tokenRange.getStart().compareTo(token) == 0) {
                        hostTokenRangeMap.get(host).add(tokenRange);
                    }
                }
            }
        }

        Map<Host, Integer> hostIndexTokenRange = new HashMap<>();
        for (Host host : allHosts) {
            hostIndexTokenRange.put(host, 0);
        }

        Queue<TokenRange> tokenRangeQueue = new LinkedList<>();
        boolean finish;
        do {
            finish = true;
            for (Host host : allHosts) {
                Integer lastIndex = hostIndexTokenRange.get(host);
                List<TokenRange> tokenRanges = hostTokenRangeMap.get(host);
                if (lastIndex >= tokenRanges.size())
                    continue;
                finish = false;
                TokenRange tokenRange = tokenRanges.get(lastIndex);
                tokenRangeQueue.add(tokenRange);
                hostIndexTokenRange.put(host, lastIndex + 1);
            }

        } while (!finish);


        PreparedStatement prepare = session.prepare("DELETE FROM traffic.emsinfo" +
                " WHERE emsinfoid = ? ");

        System.out.println("All tokens: " + tokenRangeQueue.size());
        while (tokenRangeQueue.size() != 0) {

            while (tokenRangeQueue.size() != 0) {
                TokenRange tokenRange = tokenRangeQueue.remove();
//                System.out.println("Deleting old rows token range: " + tokenRange);
                Future<TokenRangeDeletes> future = executorService.submit(
                        new TokenRangeDeletes(session, prepare, tokenRange, tsMiS));
                futures.add(future);
            }

            int num = 0;
            for (Future<TokenRangeDeletes> future : futures) {
                TokenRangeDeletes result = future.get();
                if (result.getLastId() != 0) {
                    tokenRangeQueue.add(result.getTokenRange());
                } else {
                    System.out.println("Completed delete for token range: " + result.getTokenRange() +
                            ", sum: " + result.getSum());
                    num += result.getSum();
                }
            }

            System.out.println("All old allowed rows are deleted, num: " + num);
        }
    }

    public void close() {
        session.close();
        cluster.close();
        executorService.shutdown();
    }
}
