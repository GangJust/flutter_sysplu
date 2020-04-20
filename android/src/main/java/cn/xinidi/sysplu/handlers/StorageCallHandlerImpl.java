package cn.xinidi.sysplu.handlers;

import android.app.Activity;
import android.app.Application;

import cn.xinidi.sysplu.utils.StorageUtil;
import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class StorageCallHandlerImpl implements BaseCallHandler {
    private Application application;
    private Activity activity;

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (application == null || activity == null) {
            result.error("-1", "Failed to attach to Activity!", null);
            return;
        }
        switch (call.method) {
            case "getDataDirectory":
                result.success(StorageUtil.getDataDirectory());
                return;
            case "getCacheDirectory":
                result.success(StorageUtil.getCacheDirectory());
                return;
            case "getStorageDirectory":
                result.success(StorageUtil.getStorageDirectory());
                return;
            case "getRootDirectory":
                result.success(StorageUtil.getRootDirectory());
                return;
            case "getCacheDir":
                result.success(StorageUtil.getCacheDir(activity));
                return;
            case "getStorageCacheDir":
                result.success(StorageUtil.getStorageCacheDir(activity));
                return;
            case "getFilesDir":
                result.success(StorageUtil.getFilesDir(activity));
                return;
            case "getObbDir":
                result.success(StorageUtil.getObbDir(activity));
                return;
            case "getPackageName":
                result.success(StorageUtil.getPackageName(activity));
                return;
            case "getPackageCodePath":
                result.success(StorageUtil.getPackageCodePath(activity));
                return;
            case "getPackageResourcePath":
                result.success(StorageUtil.getPackageResourcePath(activity));
                return;
        }
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}

