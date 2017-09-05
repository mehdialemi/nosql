package ir.infra.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import ir.infra.NoSqlClient;
import ir.infra.core.Constants;
import ir.infra.tables.EmsInfo;

import java.net.UnknownHostException;

/**
 * Cassandra client to insert objects into the cassandra cluster.
 * The configured keyspace is {@link Constants#KEY_SPACE} and all objects are inserted using
 * {@link MappingManager} class utility.
 */
public class Client implements NoSqlClient {

    Mapper<EmsInfo> emsInfoMapper;

    public Client(String coordinator) throws UnknownHostException {
        Cluster cluster = Cluster.builder()
                .addContactPoints(coordinator)
                .build();

        cluster.init();
        Session session = cluster.connect(Constants.KEY_SPACE);

        MappingManager mappingManager = new MappingManager(session);
        emsInfoMapper = mappingManager.mapper(EmsInfo.class);
    }

    public void add(EmsInfo emsInfo) {
        emsInfoMapper.save(emsInfo);
    }

    public EmsInfo getEmsInfo(long id) {
        EmsInfo emsInfo = new EmsInfo();
        emsInfo.setEmsInfoId(id);
        return emsInfoMapper.get(emsInfo);
    }
}
