package ir.infra.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import static ir.infra.core.Constants.KEY_SPACE;

@Table(keyspace = KEY_SPACE, name = "CodeType")
public class CodeType {

    @PartitionKey
    int Id;

    @Column
    String Description;

    @Column
    String Behavior;

    @Column
    float Percentage;
}
