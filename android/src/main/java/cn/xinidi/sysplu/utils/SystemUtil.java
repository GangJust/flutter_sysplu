package cn.xinidi.sysplu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.DataOutputStream;
import java.io.File;


public class SystemUtil {
    //安装权限状态码
    public static int REQUEST_CODE_APP_INSTALL = 998;

    /**
     * 请求ROOT权限
     * https://www.cnblogs.com/android-er/p/5478298.html
     * https://www.jb51.net/article/43411.htm
     */
    public static synchronized boolean requestRoot(Context context) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + context.getPackageCodePath();
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());

            os.writeChars(cmd + "\n");
            os.writeChars("exit\n");

            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * 判断是否具有ROOT权限
     */
    public static synchronized boolean hasRoot() {

        boolean res = false;
        File su1 = new File("/system/bin/su");
        File su2 = new File("/system/xbin/su");
        try {
            if (!su1.exists() && !su2.exists()) {
                res = false;
            } else {
                res = true;
            }
            ;
        } catch (Exception e) {

        }
        return res;
    }

    /**
     * 安装apk
     * https://www.jb51.net/article/144454.htm
     * https://blog.csdn.net/wxz1179503422/article/details/84874171
     * <p>
     * 安装apk
     * <p>
     * 1.在 AndroidManifest.xml 添加权限：
     * *     <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
     * *     <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * *     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     * <p>
     * 2.在 AndroidManifest.xml <application> 中添加：
     * <provider
     * *    android:name="androidx.core.content.FileProvider"
     * *    android:authorities="${applicationId}.fileProvider"
     * *    android:exported="false"
     * *    android:grantUriPermissions="true">
     * <meta-data
     * *    android:name="android.support.FILE_PROVIDER_PATHS"
     * *    android:resource="@xml/file_paths" />
     * </provider>
     * <p>
     * 3.在 res/xml/file_paths.xml(无则建) 中添加：
     * <?xml version="1.0" encoding="utf-8"?>
     * * <paths>
     * *    <external-path path="Android/data/包名/" name="files_path" />
     * *    <external-path path="." name="storage_path" />
     * * </paths>
     */
    public static void installApk(Context context, String apkPath) {
        if (!(new File(apkPath).exists())) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = isHasInstallPermissionWithO(context);
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(context);
                return;
            } else {
                startTypeFile(context, apkPath);
            }
        }

    }

    /**
     * 是否具有安装未知来源应用权限
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    /**
     * 跳转设置安装未知来源应用权限界面
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        Uri packageURI = Uri.parse("package:"+context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
        context.startActivity(intent);
    }

    /**
     * 打开对应文件
     * <p>
     * https://www.jianshu.com/p/74fcbca82bb0
     *
     * @param context
     */
    public static boolean startTypeFile(Context context, String path) {
        Intent intent = IntentUtil.getIntent(context, path);
        if (intent != null) {
            try {
                context.startActivity(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;

    }
}