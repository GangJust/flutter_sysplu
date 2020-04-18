package cn.xinidi.sysplu.utils;

import android.content.Context;
import android.os.Environment;

public class StorageUtil {
    //获得根目录/data内部存储路径
    public static String getDataDirectory() {
        return Environment.getDataDirectory().getPath();
    }

    //获得缓存目录/cache
    public static String getCacheDirectory() {
        return Environment.getDownloadCacheDirectory().getPath();
    }

    //获得SD卡目录/mnt/sdcard（获取的是手机外置sd卡的路径）
    public static String getStorageDirectory() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    //获得系统目录/system
    public static String getRootDirectory() {
        return Environment.getRootDirectory().getPath();
    }

    //用于获取APP的cache目录 /data/data/包名/cache
    public static String getCacheDir(Context context) {
        return context.getCacheDir().getPath();
    }

    //用于获取APP的在SD卡中的cache目
    public static String getStorageCacheDir(Context context) {
        return context.getExternalCacheDir().getPath();
    }

    //用于获取APP的cache目录 /data/data/包名/files
    public static String getFilesDir(Context context) {
        return context.getFilesDir().getPath();
    }

    //用于获取Android的obb目录
    public static String getObbDir(Context context) {
        return context.getObbDir().getPath();
    }

    //用于获取APP所在包目录
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    //用于获取当前应用程序对应的 apk 文件的路径
    public static String getPackageCodePath(Context context) {
        return context.getPackageCodePath();
    }

    //用于获取该程序的安装包路径
    public static String getPackageResourcePath(Context context) {
        return context.getPackageResourcePath();
    }
}
