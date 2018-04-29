package top.com.working_client.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by ttop on 2018/2/17.
 */

public class Permissions {
    public static void checkPermission(Activity ctx) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(ctx, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (i != PackageManager.PERMISSION_GRANTED) {
                ctx.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}
