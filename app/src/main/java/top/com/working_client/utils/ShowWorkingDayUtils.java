package top.com.working_client.utils;

import android.app.Activity;

import com.othershe.calendarview.weiget.CalendarView;

import java.util.HashMap;

import top.com.working_client.MainActivity;

/**
 * Created by ttop on 2018/2/17.
 */

public class ShowWorkingDayUtils {
    public static HashMap<String, String> data = new HashMap<>();

    public static void setWorkingDay(CalendarView calenderView,HashMap<String, String> new_map, Activity ctx) {
        data.putAll(new_map);
        MainActivity.SendHandler handler = new MainActivity.SendHandler((MainActivity) ctx);
        calenderView.setSpecifyMap(data);
        handler.sendEmptyMessage(MainActivity.FINISH);
    }

    public static String getLunar(String s) {
        if (data.size() > 0) {
            return data.get(s);
        } else {
            return null;
        }
    }
}
