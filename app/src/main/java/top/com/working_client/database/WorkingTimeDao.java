package top.com.working_client.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttop on 2018/3/13.
 */

public class WorkingTimeDao implements DaoInterface<WorkingTime> {
    private WorkingTimeHelper wsHelper;
    private Dao wsDao;

    public WorkingTimeDao(Context context) {
        try {
            wsHelper = WorkingTimeHelper.getHelper(context);
            wsDao = wsHelper.getDao(WorkingTime.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean add(WorkingTime ws) {
        try {
            wsDao.createOrUpdate(ws);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean clearAll() {
        try {
            wsDao.delete(queryEvery());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<WorkingTime> queryEvery() throws SQLException {
        List<WorkingTime> wss = new ArrayList<WorkingTime>();
        wss = (List<WorkingTime>) wsDao.queryForAll();
        return wss;
    }
}
