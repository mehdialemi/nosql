package ir.infra.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.extras.codecs.joda.InstantCodec;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import ir.infra.core.ClusterConf;
import ir.infra.core.Constants;
import ir.infra.tables.EmsInfo;

import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Cassandra client to insert objects into the cassandra cluster.
 * The configured keyspace is {@link Constants#KEY_SPACE} and all objects are inserted using
 * {@link MappingManager} class utility.
 */
public class CassandraClient {

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

    public void deleteOldAllowed() throws ExecutionException, InterruptedException, UnknownHostException {

        long tsMiS = (System.currentTimeMillis() - conf.getOld_allowed_sec() * 1000) * 1000;

        Metadata metadata = cluster.getMetadata();
        Set<Host> allHosts = cluster.getMetadata().getAllHosts();

//        String hostAddress = InetAddress.getLocalHost().getHostAddress();
//        System.out.println("Current host address: " + hostAddress);
        List<Future<Integer>> futures = new ArrayList<>();

        Set<TokenRange> allTokenRanges = metadata.getTokenRanges();

        Map<String, List<TokenRange>> hostTokenRangeMap = new HashMap<>();
        for(Host host: allHosts)
            hostTokenRangeMap.put(host.getAddress().getHostAddress(), new ArrayList<>());

        for (Host host : allHosts) {
//            if (!hostAddress.equals(host.getAddress().getHostAddress()))
//                continue;
            for (Token token : host.getTokens()) {
                for (TokenRange tokenRange : allTokenRanges) {
                    if (tokenRange.getStart().compareTo(token) == 0) {
                        hostTokenRangeMap.get(host.getAddress().getHostAddress()).add(tokenRange);
                    }
                }
            }
        }

        for (Host host : allHosts) {

            String hostAddress = host.getAddress().getHostAddress();
            List<TokenRange> tokenRanges = hostTokenRangeMap.get(hostAddress);
            System.out.println("Deleting old rows on host: " + hostAddress + ", num tokens: " + tokenRanges.size());
            Future<Integer> future = executorService.submit(
                    new TokenRangeDeletes(session, hostAddress, tokenRanges, tsMiS));
            futures.add(future);
        }
//            Set<TokenRange> tokenRanges = metadata.getTokenRanges(Constants.KEY_SPACE, host);

//            System.out.println("Current host tokens: " + tokenRanges.size());

        int sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }

        System.out.println("All processed tokens: " + sum);

    }

    public void close() {
        session.close();
        cluster.close();
        executorService.shutdown();
    }
}
