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
 * Created by ttop on 2018/2/20.
 */

public class WorkingSheetHelper_new extends OrmLiteSqliteOpenHelper {
    public static final String dataBase_name = "working_sheet_new.db";
    public static final int database_version = 1;
    private static WorkingSheetHelper_new instance;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    public static WorkingSheetHelper_new getHelper(Context context) {
        if (null == instance) {
            synchronized (WorkingSheetHelper_new.class) {
                if (null == instance) {
                    instance = new WorkingSheetHelper_new(context);
                }
            }
        }
        return instance;
    }

    private WorkingSheetHelper_new(Context context) {
        super(context, dataBase_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkingSheet.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, WorkingSheet.class, true);
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
