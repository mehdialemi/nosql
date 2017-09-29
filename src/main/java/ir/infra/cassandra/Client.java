package ir.infra.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import ir.infra.tables.Hotel;

public class Client {

    public static void main(String[] args) {
        Cluster cluster = Cluster.builder().addContactPoint("localhost")
                .build();
        cluster.init();

        Session session = cluster.connect("hotel");

//        SimpleStatement statement = new SimpleStatement("INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)"
//        , "AZ123", "Super Hotel at WestWorld", "1-888-999-9999");

//        session.execute(statement);

        PreparedStatement preparedStatement = session.prepare("INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)");

        BoundStatement bind = preparedStatement.bind();
        bind.setString("id", "3");
        bind.setString("name", "mehdi");
        bind.setString("phone", "0493493299");

        BoundStatement bind2 = preparedStatement.bind();
        bind2.setString("id", "4");
        bind2.setString("name", "ahmad");
        bind2.setString("phone", "932849328049328");

        BatchStatement batchStatement = new BatchStatement();
        batchStatement.add(bind);
        batchStatement.add(bind2);

        session.execute(batchStatement);

        Statement statement = QueryBuilder.insertInto("hotels")
                .value("id", "5")
                .value("name", "mary")
                .value("phone", "3423878475934")
                .setConsistencyLevel(ConsistencyLevel.ALL);

        session.execute(statement);


        MappingManager mappingManager = new MappingManager(session);
        Mapper<Hotel> mapper = mappingManager.mapper(Hotel.class);
        Hotel hotel = new Hotel();
        hotel.id = "10";
        hotel.name = "hotel1";
        hotel.phone = "43509348";
        mapper.save(hotel);

        Hotel hotel1 = mapper.get("10");
        System.out.println("id: " +  hotel1.id);
        System.out.println("name: " + hotel1.name);
        System.out.println("phone: " + hotel1.phone);

        Statement hotels = QueryBuilder.select().all().from("hotels").disableTracing();
        ResultSet execute = session.execute(hotels);
        for (Row row : execute) {
            System.out.format("hotel_id:%s, name: %s, phone: %s",
                    row.getString("id"), row.getString("name"), row.getString("phone"));
            System.out.println();
        }
    }
}
