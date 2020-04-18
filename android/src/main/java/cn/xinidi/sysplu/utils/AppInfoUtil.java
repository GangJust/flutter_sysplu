package cn.xinidi.sysplu.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class AppInfoUtil {
    /**
     * 获取本地 Apk ApplicationInfo
     *
     * @param packageManager
     * @param apkPath
     * @return
     */
    private static ApplicationInfo _getApplicationInfo(PackageManager packageManager, String apkPath) {
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (packageInfo != null) {
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;

            return appInfo;
        }
        return null;
    }

    /**
     * 获取Apk图标
     *
     * @param context
     * @param apkPath
     * @return 字节组
     */
    public static byte[] getApkIcon(Context context, String apkPath) {
        if (!(new File(apkPath).exists())) return null;

        PackageManager pm = context.getPackageManager();
        ApplicationInfo appInfo = _getApplicationInfo(pm, apkPath);
        if (appInfo != null) {
            return _getAppIcon(pm, appInfo);
        }
        return null;
    }

    /**
     * 获取Apk图标
     *
     * @param context
     * @param apkPath
     * @param toPath
     * @return 欲输出的位置
     */
    public static String getApkIcon(Context context, String apkPath, String toPath) {
        if (!(new File(apkPath).exists())) return null;

        byte[] bytes = getApkIcon(context, apkPath);
        if (bytes != null) {
            File file = new File(toPath);
            try {
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                return e.getMessage();
            }
            return file.getPath();
        }

        return null;
    }

    /**
     * 获取已安装的Apk图标
     *
     * @param context
     * @param packageName
     * @return
     */
    public static byte[] getInstallApkIcon(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            ApplicationInfo appInfo = packInfo.applicationInfo;
            if (appInfo != null) {
                return _getAppIcon(pm, appInfo);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取已安装的Apk图标
     *
     * @param context
     * @param packageName
     * @param toPath
     * @return 位置
     */
    public static String getInstallApkIcon(Context context, String packageName, String toPath) {
        byte[] bytes = getInstallApkIcon(context, packageName);
        if (bytes != null) {
            File file = new File(toPath);
            try {
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file.getPath();
        }

        return null;
    }

    /**
     * 获取Apk信息
     *
     * @param context
     * @param apkPath
     * @return
     */
    public static Map<String, Object> getAppInfo(Context context, String apkPath) {
        if (!(new File(apkPath).exists())) return null;

        PackageManager pm = context.getPackageManager();
        PackageInfo packInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        ApplicationInfo appInfo = _getApplicationInfo(pm, apkPath);
        if (appInfo == null) return null;

        //未安装apk
        Map<String, Object> map = new HashMap<>();
        map.put("packageName", packInfo.packageName);
        map.put("versionName", packInfo.versionName);
        map.put("versionCode", packInfo.versionCode);
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.put("minSdkVersion", appInfo.minSdkVersion);
        }
        map.put("targetSdkVersion", appInfo.targetSdkVersion);
        map.put("appName", appInfo.loadLabel(pm).toString());

        //如果当前应用已安装
        Map<String, Object> installMap = getInstallApkInfo(context, packInfo.packageName);
        if (installMap != null) return installMap;

        return map;
    }

    /**
     * 获取已安装Apk信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Map<String, Object> getInstallApkInfo(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return _getInstallApkInfo(pm, packInfo);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /**
     * 取Apk信息
     *
     * @param pm
     * @param packInfo
     * @return
     */
    private static Map<String, Object> _getInstallApkInfo(PackageManager pm, PackageInfo packInfo) {
        Map<String, Object> map = new HashMap<>();
        ApplicationInfo appInfo = packInfo.applicationInfo;
        map.put("packageName", packInfo.packageName);
        map.put("versionName", packInfo.versionName);
        map.put("versionCode", packInfo.versionCode);
        map.put("firstInstallTime", packInfo.firstInstallTime);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.put("minSdkVersion", appInfo.minSdkVersion);
        }
        map.put("targetSdkVersion", appInfo.targetSdkVersion);
        map.put("appName", appInfo.loadLabel(pm).toString());
        map.put("lastUpdateTime", packInfo.lastUpdateTime);
        map.put("dataDir", appInfo.dataDir);
        map.put("sourceDir", appInfo.sourceDir);
        map.put("publicSourceDir", appInfo.publicSourceDir);
        map.put("uid", appInfo.uid);

        return map;
    }

    /**
     * 取Apk图标
     *
     * @param pm
     * @param appInfo
     * @return
     */
    private static synchronized byte[] _getAppIcon(PackageManager pm, ApplicationInfo appInfo) {
        Drawable drawable = appInfo.loadIcon(pm);
        Bitmap bitmap = drawableToBitmap(drawable);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, arrayOutputStream);
        return arrayOutputStream.toByteArray();
    }

    /**
     * 已安装的所有App
     *
     * @param context
     * @param type    0 用户应用
     *                1 系统应用
     *                -1 所有应用
     */
    public static List<Map<String, Object>> installAppAll(Context context, int type) {
        List<Map<String, Object>> userList = new ArrayList();
        List<Map<String, Object>> systemList = new ArrayList();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packInfo : packInfos) {
            if ((packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                systemList.add(_getInstallApkInfo(pm, packInfo));
            } else {
                userList.add(_getInstallApkInfo(pm, packInfo));
            }
        }

        if (type == 0) {
            return userList;
        } else if (type == 1) {
            return systemList;
        }

        List<Map<String, Object>> allList = new ArrayList();
        allList.addAll(userList);
        allList.addAll(systemList);
        return allList;
    }

    /**
     * 提取Apk
     *
     * @param baseApk
     * @param toPath
     * @return 成功返回路径，否则返回null
     */
    public synchronized static String exportApk(String baseApk, String toPath) {
        File inFile = new File(baseApk);
        if (!inFile.exists()) return null;

        try {
            File toFile = new File(toPath);

            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(toFile);
            byte[] bytes = new byte[2048];
            int i;
            while ((i = in.read(bytes)) != -1) {
                out.write(bytes, 0, i);
            }
            in.close();
            out.flush();
            out.close();

            return toFile.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Drawable转Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            bitmap = Bitmap.createBitmap(w, h, config);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
        }

        return bitmap;
    }
}
