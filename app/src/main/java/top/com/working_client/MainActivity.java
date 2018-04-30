package top.com.working_client;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.FindCallback;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.utils.CalendarUtil;
import com.othershe.calendarview.weiget.CalendarView;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import top.com.working_client.database.WorkingSheet;
import top.com.working_client.database.WorkingSheetDao_new;
import top.com.working_client.database.WorkingTime;
import top.com.working_client.database.WorkingTimeDao;
import top.com.working_client.utils.ConverUtil;
import top.com.working_client.utils.DataUtil;
import top.com.working_client.utils.Permissions;
import top.com.working_client.utils.ServerData;
import top.com.working_client.utils.ShowWorkingDayUtils;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView chooseDate;
    private CalendarView calenderView;
    private static HashMap<String, String> dataList_new = new HashMap<>();
    private static WorkingTime dataList_time;
    private int[] mDate = CalendarUtil.getCurrentDate();

    public static final int FRESH = 0x000001;
    public static final int DATABASE = 0x000004;
    public static final int FINISH = 0x000002;
    public static final int ERROR = 0x000003;

    public static final String TAG = "TTT";
    private static RelativeLayout pre_show;
    private static RelativeLayout after_show;
    private static TextView text_pre_show;
    private TextView item_today_title;
    private TextView item_today_date;
    private TextView item_today_gotime;
    private TextView item_today_banci;
    private TextView item_tomorrow_title;
    private TextView item_tomorrow_date;
    private TextView item_tomorrow_gotime;
    private TextView item_tomorrow_banci;
    private TextView item_today_leavetime;
    private TextView item_tomorrow_leavetime;
    private String mTodayDate;
    private WorkingSheetDao_new new_mDao;
    private WorkingTimeDao mTimeDao;
    //网络是否进入标志
    private boolean flag1 = false;
    private boolean flag2 = false;
    //网络是否成功标志
    private boolean flag3 = false;
    private boolean flag4 = false;
    private boolean netisSuccess = false;
    private ProgressBar mProgress;
    private String mAppId;
    private String mAppKey;

    public static class SendHandler extends Handler {
        private WeakReference<MainActivity> weakReference;

        public SendHandler(MainActivity activity) {
            weakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = weakReference.get();
            if (msg.what == FRESH) {
                ShowWorkingDayUtils.setWorkingDay(activity.calenderView, dataList_new, activity);
            } else if (msg.what == FINISH) {
                pre_show.setVisibility(View.INVISIBLE);
                after_show.setVisibility(View.VISIBLE);
                activity.calenderView.init();
                activity.bottom_show(activity.mTodayDate);
            } else if (msg.what == ERROR) {
                text_pre_show.setText("网络出现错误！！");
            } else if (msg.what == DATABASE) {
                activity.pullDataBase();
            }
        }
    }

    SendHandler myHandler = new SendHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请权限
        Permissions.checkPermission(this);
        //从服务器读取文件
//        try {
//            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager
//                .GET_META_DATA);
//            mAppId = info.metaData.getString("appkey");
//            mAppKey = info.metaData.getString("appvalue");
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        mAppId=BuildConfig.appkey;
            mAppKey=BuildConfig.appvalue;
        AVOSCloud.initialize(this, mAppId, mAppKey);
        pullServer();

        int[] currentDate = CalendarUtil.getCurrentDate();
        mTodayDate = currentDate[0] + "." + currentDate[1] + "." + currentDate[2];

        //控件
        title = (TextView) findViewById(R.id.title);
        chooseDate = (TextView) findViewById(R.id.choose_date);
        calenderView = (CalendarView) findViewById(R.id.calendar);
        pre_show = (RelativeLayout) findViewById(R.id.pre_show);
        after_show = (RelativeLayout) findViewById(R.id.after_show);
        text_pre_show = (TextView) findViewById(R.id.text_pre_show);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        //显示控件
        item_today_title = (TextView) findViewById(R.id.item_today_title);
        item_today_date = (TextView) findViewById(R.id.item_today_date);
        item_today_gotime = (TextView) findViewById(R.id.item_today_gotime);
        item_today_leavetime = (TextView) findViewById(R.id.item_today_leavetime);
        item_today_banci = (TextView) findViewById(R.id.item_today_banci);


        item_tomorrow_title = (TextView) findViewById(R.id.item_tomorrow_title);
        item_tomorrow_date = (TextView) findViewById(R.id.item_tomorrow_date);
        item_tomorrow_gotime = (TextView) findViewById(R.id.item_tomorrow_gotime);
        item_tomorrow_leavetime = (TextView) findViewById(R.id.item_tomorrow_leavetime);
        item_tomorrow_banci = (TextView) findViewById(R.id.item_tomorrow_banci);

        //设置日历
        calenderView.setStartEndDate("2017.1", "2028.12")
            .setDisableStartEndDate("2016.10.10", "2028.10.10")
            .setInitDate(mDate[0] + "." + mDate[1])
            .setSingleDate(mDate[0] + "." + mDate[1] + "." + mDate[2]);
        title.setText(mDate[0] + "年" + mDate[1] + "月");

        calenderView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
            }
        });

        calenderView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                title.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月");
                if (date.getType() == 1) {
                    bottom_show(date.getSolar()[0] + "." + date.getSolar()[1] + "." + date.getSolar()[2]);
                }
            }
        });

    }


    private void bottom_show(String date) {
        int[] dates = DataUtil.formatDateArray(date);
        String nextDay = DataUtil.convertNextDay(dates[0], dates[1], dates[2]);

        //设置日期
        item_today_date.setText("日期:" + DataUtil.formatDate(date));
        item_tomorrow_date.setText("日期:" + DataUtil.formatDate(nextDay));

        //设置班次
        String today_lunar = ShowWorkingDayUtils.getLunar(date);
        String tomorrow_lunar = ShowWorkingDayUtils.getLunar(nextDay);

        if (today_lunar == null || today_lunar.equals("")) {
            item_today_banci.setText("班次:无");
        } else {
            item_today_banci.setText("班次:" + today_lunar + "班");
        }
        if (tomorrow_lunar == null || tomorrow_lunar.equals("")) {
            item_tomorrow_banci.setText("班次:无");
        } else {
            item_tomorrow_banci.setText("班次:" + tomorrow_lunar + "班");
        }
        if (dataList_time == null && !netisSuccess) {
            //设置今天上班时间
            if (today_lunar == null || today_lunar.equals("")) {
                item_today_gotime.setText("出发时间: 无");
                item_today_leavetime.setText("下班时间:无");
            } else {
                switch (today_lunar) {
                    case "早":
                        item_today_gotime.setText(dataList_time.getMorning_go());
                        item_today_leavetime.setText(dataList_time.getMorning_leave());
                        break;
                    case "中":
                        item_today_gotime.setText(dataList_time.getAfertnoon_gotime());
                        item_today_leavetime.setText(dataList_time.getAfertnon_leavetime());
                        break;
                    case "晚":
                        item_today_gotime.setText(dataList_time.getEvening_gotime());
                        item_today_leavetime.setText(dataList_time.getEvening_leavetime());
                        break;
                    case "休":
                        item_today_gotime.setText("出发时间: 无");
                        item_today_leavetime.setText("下班时间： 无");
                        break;
                    case "白":
                        item_today_gotime.setText(dataList_time.getBai_gotime());
                        item_today_leavetime.setText(dataList_time.getBai_leavetime());
                        break;
                }
            }
            //设置下一天上班时间
            if (tomorrow_lunar == null || tomorrow_lunar.equals("")) {
                item_tomorrow_gotime.setText("出发时间: 无");
                item_tomorrow_leavetime.setText("下班时间: 无");
            } else {
                switch (tomorrow_lunar) {
                    case "早":
                        item_tomorrow_gotime.setText(dataList_time.getEvening_gotime());
                        item_tomorrow_leavetime.setText(dataList_time.getEvening_leavetime());
                        break;
                    case "中":
                        item_tomorrow_gotime.setText(dataList_time.getAfertnoon_gotime());
                        item_tomorrow_leavetime.setText(dataList_time.getAfertnon_leavetime());
                        break;
                    case "晚":
                        item_tomorrow_gotime.setText(dataList_time.getEvening_gotime());
                        item_tomorrow_leavetime.setText(dataList_time.getEvening_leavetime());
                        break;
                    case "白":
                        item_tomorrow_gotime.setText(dataList_time.getBai_gotime());
                        item_tomorrow_leavetime.setText(dataList_time.getBai_leavetime());
                        break;
                    case "休":
                        item_tomorrow_gotime.setText("出发时间: 无");
                        item_tomorrow_leavetime.setText("下班时间： 无");
                        break;
                }

            }
        } else {
            if (today_lunar == null || today_lunar.equals("")) {
                item_today_gotime.setText("出发时间: 无");
                item_today_leavetime.setText("下班时间:无");
            } else {
                switch (today_lunar) {
                    case "早":
                        item_today_gotime.setText("出发时间:" + dataList_time.getMorning_go());
                        item_today_leavetime.setText("下班时间:" + dataList_time.getMorning_leave());
                        break;
                    case "中":
                        item_today_gotime.setText("出发时间:" + dataList_time.getAfertnoon_gotime());
                        item_today_leavetime.setText("下班时间:" + dataList_time.getAfertnon_leavetime());
                        break;
                    case "晚":
                        item_today_gotime.setText("出发时间:" + dataList_time.getEvening_gotime());
                        item_today_leavetime.setText("下班时间:" + dataList_time.getEvening_leavetime());
                        break;
                    case "休":
                        item_today_gotime.setText("出发时间: 无");
                        item_today_leavetime.setText("下班时间： 无");
                        break;
                    case "白":
                        item_today_gotime.setText("出发时间:" + dataList_time.getBai_gotime());
                        item_today_leavetime.setText("下班时间:" + dataList_time.getBai_leavetime());
                        break;
                }
            }
            //设置下一天上班时间
            if (tomorrow_lunar == null || tomorrow_lunar.equals("")) {
                item_tomorrow_gotime.setText("出发时间: 无");
                item_tomorrow_leavetime.setText("下班时间: 无");
            } else {
                switch (tomorrow_lunar) {
                    case "早":
                        item_tomorrow_gotime.setText("出发时间:" + dataList_time.getMorning_go());
                        item_tomorrow_leavetime.setText("下班时间:" + dataList_time.getMorning_leave());
                        break;
                    case "中":
                        item_tomorrow_gotime.setText("出发时间:" + dataList_time.getAfertnoon_gotime());
                        item_tomorrow_leavetime.setText("下班时间:" + dataList_time.getAfertnon_leavetime());
                        break;
                    case "晚":
                        item_tomorrow_gotime.setText("出发时间:" + dataList_time.getEvening_gotime());
                        item_tomorrow_leavetime.setText("下班时间:" + dataList_time.getEvening_leavetime());
                        break;
                    case "白":
                        item_tomorrow_gotime.setText("出发时间:" + dataList_time.getBai_gotime());
                        item_tomorrow_leavetime.setText("下班时间:" + dataList_time.getBai_leavetime());
                        break;
                    case "休":
                        item_tomorrow_gotime.setText("出发时间: 无");
                        item_tomorrow_leavetime.setText("下班时间： 无");
                        break;
                }

            }

        }
    }

    private void pullDataBase() {
        //从数据库中读取数据
        new_mDao = new WorkingSheetDao_new(this);
        mTimeDao = new WorkingTimeDao(this);
        List<WorkingSheet> new_workingSheets = new ArrayList<WorkingSheet>();
        List<WorkingTime> workingTimes = new ArrayList<WorkingTime>();
        try {
            new_workingSheets = new_mDao.queryEvery();
            workingTimes = mTimeDao.queryEvery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (workingTimes.size() > 0 && new_workingSheets.size() > 0) {
            dataList_time = workingTimes.get(0);
            dataList_new = ConverUtil.converDatabaseData(new_workingSheets, this);
            Log.e(TAG, "pullServer: 数据库读取成功");
            myHandler.sendEmptyMessage(FRESH);
        } else {
            Log.e(TAG, "pullServer: 数据库读取失败");
            myHandler.sendEmptyMessage(ERROR);
        }
    }

    public void pullServer() {
        //从服务器初始化并读取数据
        ServerData.getNewDatas(this, new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                flag1 = true;
                if (list != null && list.size() > 0) {
                    flag3 = true;
                    dataList_new = ConverUtil.converInternetData(list, getApplication());
                }
                if (flag1 & flag2) {
                    if ((flag3 & flag4)) {
                        Log.e(TAG, "网络读取成功");
                        myHandler.sendEmptyMessage(FRESH);
                    } else {
                        Log.e(TAG, "网络读取失败");
                        myHandler.sendEmptyMessage(DATABASE);
                    }
                }

            }
        });
        ServerData.getTime(this, new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                flag2 = true;
                if (list != null && list.size() > 0) {
                    flag4 = true;
                    dataList_time = ConverUtil.converInternetTime(list, getApplication());
                }
                if (flag1 & flag2) {
                    if ((flag3 & flag4)) {
                        Log.e(TAG, "new: 网络读取成功");
                        myHandler.sendEmptyMessage(FRESH);
                    } else {
                        Log.e(TAG, "new: 网络读取失败");
                        myHandler.sendEmptyMessage(DATABASE);
                    }
                }

            }
        });
    }

    public void GoToday(View v) {
        calenderView.today();
        bottom_show(mTodayDate);
    }


    public void lastMonth(View view) {
        calenderView.lastMonth();
    }

    public void nextMonth(View view) {
        calenderView.nextMonth();
    }

    //申请权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
        grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请开启相关权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
