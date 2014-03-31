package info.matsumana.logger;

import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.UtilLoggingJdbcLogger;

import java.util.LinkedList;

public class MyJdbcLogger extends UtilLoggingJdbcLogger {

    private static final int DEFAULT_LIMIT_SIZE = 3;

    private ThreadLocal<LinkedList<Sql<?>>> sqlLogdList = new ThreadLocal<>();

    @Override
    public void logSql(String callerClassName, String callerMethodName, Sql<?> sql) {
        add(sql);
        super.logSql(callerClassName, callerMethodName, sql);
    }

    public Sql<?> getLast() {
        return isEmpty() ? null : getSqlLogList().getLast();
    }

    private void add(Sql<?> sql) {
        LinkedList<Sql<?>> list = getSqlLogList();
        list.add(sql);
        if (list.size() > DEFAULT_LIMIT_SIZE) {
            list.removeFirst();
        }
    }

    private LinkedList<Sql<?>> getSqlLogList() {
        LinkedList<Sql<?>> list = (LinkedList<Sql<?>>) sqlLogdList.get();
        if (list == null) {
            list = new LinkedList<>();
            sqlLogdList.set(list);
        }

        return list;
    }

    private boolean isEmpty() {
        return getSqlLogList().size() == 0;
    }
}
