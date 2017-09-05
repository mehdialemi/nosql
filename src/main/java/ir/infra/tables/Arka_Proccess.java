package ir.infra.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.joda.time.DateTime;

import static ir.infra.core.Constants.KEY_SPACE;

@Table(keyspace = KEY_SPACE, name = "Arka_Proccess")
public class Arka_Proccess {

    @PartitionKey
    long EmsInfoId;

    @Column
    int SubClassId;

    @Column
    long Plate;

    @Column
    int ColorId;

    @Column
    int SubClass_Acc;

    @Column
    int Plate_Acc;

    @Column
    int Color_Acc;

    @Column
    DateTime CreateDate;
}
