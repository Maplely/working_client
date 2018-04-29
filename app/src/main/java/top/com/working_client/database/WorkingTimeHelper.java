package top.com.working_client.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ttop on 2018/3/13.
 */

public class WorkingTimeHelper extends OrmLiteSqliteOpenHelper {
    public static final String dataBase_name = "workingtime.db";
    public static final int database_version = 1;
    private static WorkingTimeHelper instance;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    public static WorkingTimeHelper getHelper(Context context) {
        if (null == instance) {
            synchronized (WorkingTimeHelper.class) {
                if (null == instance) {
                    instance = new WorkingTimeHelper(context);
                }
            }
        }
        return instance;
    }

    private WorkingTimeHelper(Context context) {
        super(context, dataBase_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkingTime.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, WorkingTime.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }else {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }

        return dao;
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
