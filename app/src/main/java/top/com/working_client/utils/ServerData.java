package top.com.working_client.utils;

import android.app.Activity;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.PushService;

/**
 * Created by ttop on 2018/2/17.
 */

public class ServerData {

    private static AVQuery<AVObject> workTask_new;
    private static AVQuery<AVObject> workTask_time;

    public static void getNewDatas(Activity activity, FindCallback<AVObject> callback) {
        PushService.setDefaultPushCallback(activity, activity.getClass());
        workTask_new = new AVQuery<>("WorkTask_new");
        workTask_new.findInBackground(callback);
    }
    public static void getTime(Activity activity, FindCallback<AVObject> callback) {
        PushService.setDefaultPushCallback(activity, activity.getClass());
        workTask_time = new AVQuery<>("WorkTask_time");
        workTask_time.findInBackground(callback);
    }

}
