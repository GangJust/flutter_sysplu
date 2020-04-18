package cn.xinidi.sysplu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class PermissionUtil {
    private static int P_CODE = 999;

    /**
     * 请求权限
     *
     * @param activity
     * @param permissions
     */
    public static void requestPermission(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) return;

        ArrayList<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (!checkPermission(activity, permission)) list.add(permission);
        }
        if (!list.isEmpty())
            ActivityCompat.requestPermissions(activity, list.toArray(new String[list.size()]), P_CODE);
    }

    /**
     * 检查权限
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean checkPermission(Activity activity, String permission) {
        int status = ContextCompat.checkSelfPermission(activity, permission);
        if (status == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    /**
     * 是否应该显示申请权限
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean shouldShowRequest(Activity activity, String permission) {
        boolean shouldShow = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        return shouldShow;
    }

    /**
     * 跳转至本应用详情页
     *
     * @param activity
     */
    public static void toSelfSetting(Activity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        activity.startActivity(localIntent);
    }
}
