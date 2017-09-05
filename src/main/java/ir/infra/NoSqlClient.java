package ir.infra;

import ir.infra.tables.EmsInfo;

import java.io.IOException;

/**
 * Defines the necessary methods for each objects which should be persisted into the nosql database.
 */
public interface NoSqlClient {

    void add(EmsInfo emsInfo) throws IOException;

    EmsInfo getEmsInfo(long id) throws IOException;
}
