package ir.infra.cassandra;

import ir.infra.core.Constants;
import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;

/**
 * Delete rows from {@link Constants#KEY_SPACE} keyspace {@link Constants#TABLE}
 * older than specified input argument and having allowed = true
 */
public class TokenRangeDeletes implements Callable<Integer> {

    public static final String ID = "emsinfoid";
    public static final String ALLOWED = "allowed";
    private static DecimalFormat df2 = new DecimalFormat(".####");
    private final Session session;
    private String hostname;
    private final List<TokenRange> tokenRanges;
    private final long ts;

    /**
     * Constructor to receive delete token range parameter
     *
     * @param session     connected session
     * @param hostname    hostname of the target cassandra node
     * @param tokenRanges ranges of tokens for host
     * @param ts          timestamp in micro second to delete old rows with field allowed = true
     */
    public TokenRangeDeletes(final Session session, String hostname, final List<TokenRange> tokenRanges, final long ts) {
        this.session = session;
        this.hostname = hostname;
        this.tokenRanges = tokenRanges;
        this.ts = ts;
    }

    @Override
    public Integer call() throws Exception {
        PreparedStatement prepare = session.prepare("DELETE FROM traffic.emsinfo" +
                " USING TIMESTAMP ? WHERE emsinfoid = ? ");

        double tokens = tokenRanges.size();
        int completed = 0;

        for (TokenRange tokenRange : tokenRanges) {

            List<Select> selects = new ArrayList<>();
            long start = (long) tokenRange.getStart().getValue();
            long end = (long) tokenRange.getEnd().getValue();

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

            for (Select select : selects) {
                System.out.println("Executing query: " + select);
                ResultSet rows = session.execute(select);

                System.out.println("Executing delete for current token range: " + tokenRange);
                for (Row row : rows) {
                    BoundStatement boundStatement = prepare.bind(ts, row.get(ID, Long.class));
                    session.executeAsync(boundStatement);
                }

            }
            completed++;
            double progress = Math.floor(1000 * completed / tokens) / 10;
            System.out.println("Delete progress on " + hostname + "(" + completed + "/" + tokens + "):" + df2.format(progress) + "%");

            selects.clear();
        }

        return completed;
    }
}
