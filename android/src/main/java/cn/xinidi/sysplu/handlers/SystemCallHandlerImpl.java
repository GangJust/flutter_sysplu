package cn.xinidi.sysplu.handlers;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.util.Log;

import cn.xinidi.sysplu.utils.SystemUtil;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class SystemCallHandlerImpl implements BaseCallHandler {
    private Application application;
    private Activity activity;

    @Override
    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (application == null || activity == null) {
            result.error("-1", "Failed to attach to Activity!", null);
            return;
        }

        if (call.method.equals("requestRoot")) {
            result.success(SystemUtil.requestRoot(activity));
        }

        if (call.method.equals("hasRoot")) {
            result.success(SystemUtil.hasRoot());
        }

        if (call.method.equals("isHasInstallPermissionWithO")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                result.success(SystemUtil.isHasInstallPermissionWithO(activity));
                return;
            }
            result.success(true);
        }

        if (call.method.equals("startInstallPermissionSettingActivity")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SystemUtil.startInstallPermissionSettingActivity(activity);
            }
        }

        if (call.method.equals("installApk")) {
            String apk = new StringBuffer().append(call.arguments).toString();
            SystemUtil.installApk(activity, apk);
        }

        if (call.method.equals("startTypeFile")) {

            String filePath = new StringBuffer().append(call.arguments).toString();

            if (filePath != null) {
                result.success(SystemUtil.startTypeFile(activity, filePath));
            }
        }
    }
}
