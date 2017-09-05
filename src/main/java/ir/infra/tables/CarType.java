package ir.infra.tables;


import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import static ir.infra.core.Constants.KEY_SPACE;

@Table(keyspace = KEY_SPACE, name = "CarType")
public class CarType {

    @PartitionKey
    int TypeId;

    @Column
    String Type;
}
