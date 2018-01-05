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
    public static final String WT = "WT";
    private final Session session;
    private PreparedStatement prepare;
    private final TokenRange tokenRange;
    private final long ts;
    private long lastId;
    private int sum = 0;

    /**
     * Constructor to receive delete token range parameter
     *
     * @param session    connected session
     * @param tokenRange range of token for host
     * @param ts         timestamp in micro second to delete old rows with field allowed = true
     */
    public TokenRangeDeletes(final Session session, PreparedStatement prepare, final TokenRange tokenRange, final long ts) {
        this.session = session;
        this.prepare = prepare;
        this.tokenRange = tokenRange;
        this.ts = ts;
    }

    @Override
    public TokenRangeDeletes call() throws Exception {
        List<Statement> statements = new ArrayList<>();
        long start = (long) getTokenRange().getStart().getValue();
        long end = (long) getTokenRange().getEnd().getValue();

        if (lastId != 0)
            start = lastId;

        if (start > end) {
            Statement st = QueryBuilder.select()
                    .column(ID).column(ALLOWED)
                    .writeTime(ALLOWED).as(WT)
                    .from(Constants.KEY_SPACE, Constants.TABLE)
                    .where(gt(token(ID), token(start)));
            statements.add(st);

            st = QueryBuilder.select()
                    .column(ID).column(ALLOWED)
                    .writeTime(ALLOWED).as(WT)
                    .from(Constants.KEY_SPACE, Constants.TABLE)
                    .where(lt(token(ID), token(end)));
            statements.add(st);

        } else {
            Statement st = QueryBuilder.select()
                    .column(ID).column(ALLOWED)
                    .writeTime(ALLOWED).as(WT)
                    .from(Constants.KEY_SPACE, Constants.TABLE)
                    .where(gt(token(ID), token(start)))
                    .and(lt(token(ID), token(end)));
            statements.add(st);
        }

        lastId = start;
        int ignore = 0;
        try {
            for (Statement select : statements) {
                ResultSet rows = session.execute(select);

                for (Row row : rows) {
                    Long id = row.get(ID, Long.class);
                    long ws = row.get(WT, Long.class);
                    boolean allowed = row.get(ALLOWED, Boolean.class);

                    if (!allowed || ws > ts)
                        continue;

                    BoundStatement boundStatement = prepare.bind(id);
                    session.executeAsync(boundStatement);
                    sum = sum + 1;
                    lastId = id;

                    if (sum % 1000 == 0)
                        System.out.println("Num deletes for token range " + getTokenRange() + ": " + sum);
                }
            }

            System.out.println("Ignore: " + ignore);
            lastId = 0;
        } catch (Exception e) {
            System.out.println("Incomplete delete for token range: " + getTokenRange() + ", errorMsg: " + e.getMessage());
        }

        return this;
    }

    public long getLastId() {
        return lastId;
    }

    public TokenRange getTokenRange() {
        return tokenRange;
    }

    public int getSum() {
        return sum;
    }
}
