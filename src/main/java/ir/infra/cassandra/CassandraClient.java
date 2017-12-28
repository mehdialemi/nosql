package ir.infra.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.extras.codecs.joda.InstantCodec;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import ir.infra.core.ClusterConf;
import ir.infra.core.Constants;
import ir.infra.tables.EmsInfo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

        executorService = Executors.newFixedThreadPool(cluster.getMetadata().getAllHosts().size());
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

    public void deleteOldAllowed() throws ExecutionException, InterruptedException {

        long tsMiS = (System.currentTimeMillis() - conf.getOld_allowed_sec() * 1000) * 1000;

        Metadata metadata = cluster.getMetadata();
        Set<Host> allHosts = cluster.getMetadata().getAllHosts();

        List<Future<Integer>> futures = new ArrayList<>();
        for (Host host : allHosts) {
            Future<Integer> future = executorService.submit(
                    new TokenRangeDeletes(session, host.getAddress().getHostName(), metadata.getTokenRanges(Constants.KEY_SPACE, host), tsMiS));
            futures.add(future);
        }

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
