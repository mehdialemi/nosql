package ir.infra.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import static ir.infra.core.Constants.KEY_SPACE;

@Table(keyspace = KEY_SPACE, name = "Arka_SubClass")
public class Arka_SubClass {

    @PartitionKey
    int ID;

    @Column
    int ClassId;

    @Column
    String SubClass;
}
