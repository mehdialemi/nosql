package ir.infra.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import ir.infra.core.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;

/**
 * Delete rows from {@link Constants#KEY_SPACE} keyspace {@link Constants#TABLE}
 * older than specified input argument and having allowed = true
 */
public class TokenRangeDeletes implements Callable<TokenRangeDeletes> {

    public static final String ID = "emsinfoid";
    public static final String ALLOWED = "allowed";
    private final Session session;
    private final TokenRange tokenRange;
    private final long ts;
    private long lastId;

    /**
     * Constructor to receive delete token range parameter
     *
     * @param session    connected session
     * @param tokenRange range of token for host
     * @param ts         timestamp in micro second to delete old rows with field allowed = true
     */
    public TokenRangeDeletes(final Session session, final TokenRange tokenRange, final long ts) {
        this.session = session;
        this.tokenRange = tokenRange;
        this.ts = ts;
    }

    @Override
    public TokenRangeDeletes call() throws Exception {
        PreparedStatement prepare = session.prepare("DELETE FROM traffic.emsinfo" +
                " USING TIMESTAMP ? WHERE emsinfoid = ? ");

        int sum = 0;
        List<Select> selects = new ArrayList<>();
        long start = (long) getTokenRange().getStart().getValue();
        long end = (long) getTokenRange().getEnd().getValue();

        if (lastId != 0)
            start = lastId;

        if (start > end) {
            Select select = QueryBuilder.select()
                    .column(ID)
                    .from(Constants.KEY_SPACE, Constants.TABLE)
                    .where(gt(token(ID), token(start)))
                    .and(eq(ALLOWED, true))
                    .allowFiltering();
            selects.add(select);

            select = QueryBuilder.select()
                    .column(ID)
                    .from(Constants.KEY_SPACE, Constants.TABLE)
                    .where(lt(token(ID), token(end)))
                    .and(eq(ALLOWED, true))
                    .allowFiltering();
            selects.add(select);

        } else {
            Select select = QueryBuilder.select()
                    .column(ID)
                    .from(Constants.KEY_SPACE, Constants.TABLE)
                    .where(gt(token(ID), token(start)))
                    .and(lt(token(ID), token(end)))
                    .and(eq(ALLOWED, true))
                    .allowFiltering();
            selects.add(select);
        }

        lastId = start;
        try {
            for (Select select : selects) {
                System.out.println("Executing query: " + select);
                ResultSet rows = session.execute(select);

                for (Row row : rows) {
                    Long id = row.get(ID, Long.class);
                    lastId = id;
                    BoundStatement boundStatement = prepare.bind(ts, id);
                    session.executeAsync(boundStatement);
                    sum++;

                    if (sum % 1000 == 0)
                        System.out.println("Num allowed for token range " + getTokenRange() + ": " + sum);
                }
            }
            lastId = 0;
        } catch (Exception e) {
            System.out.println("Incomplete delete for token range: " + getTokenRange());
        }

        return this;
    }

    public long getLastId() {
        return lastId;
    }

    public TokenRange getTokenRange() {
        return tokenRange;
    }
}
