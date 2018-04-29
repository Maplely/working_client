package top.com.working_client.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import top.com.working_client.database.WorkingSheet;

/**
 * Created by ttop on 2018/2/20.
 */

public class WorkingSheetDao_new implements DaoInterface<WorkingSheet>{
    private WorkingSheetHelper_new wsHelper;
    private Dao wsDao;

    public WorkingSheetDao_new(Context context) {
        try {
            wsHelper = WorkingSheetHelper_new.getHelper(context);
            wsDao = wsHelper.getDao(WorkingSheet.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean add(WorkingSheet ws) {
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

    public List<WorkingSheet> queryEvery() throws SQLException {
        List<WorkingSheet> wss = new ArrayList<>();
        wss = (List<WorkingSheet>) wsDao.queryForAll();
        return wss;
    }

}
