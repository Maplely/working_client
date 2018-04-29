package top.com.working_client.database;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ttop on 2018/2/27.
 */

public interface DaoInterface<T>{
    public Boolean add (T t);
    public boolean clearAll();
    public List queryEvery()throws SQLException;
}
