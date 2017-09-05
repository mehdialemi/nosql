package ir.infra.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import static ir.infra.core.Constants.KEY_SPACE;

@Table(keyspace = KEY_SPACE, name = "Arka_Class")
public class Arka_Class {

    @PartitionKey
    private
    int Id;

    @Column
    private
    String Title;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
