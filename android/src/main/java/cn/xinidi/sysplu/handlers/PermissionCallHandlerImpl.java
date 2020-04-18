package cn.xinidi.sysplu.handlers;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

import cn.xinidi.sysplu.Listeners;
import cn.xinidi.sysplu.utils.PermissionUtil;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * 权限相关
 */
public class PermissionCallHandlerImpl implements BaseCallHandler {
    private Application application;
    private Activity activity;
    private ActivityPluginBinding binding;

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (activity == null || application == null || binding == null) {
            result.error("-1", "Failed to attach to Activity!", null);
            return;
        }
        if (call.method.equals("checkPermission")) {
            String permission = new StringBuffer().append(call.arguments).toString();
            result.success(PermissionUtil.checkPermission(activity, permission));
            return;
        }

        if (call.method.equals("requestPermission")) {
            ArrayList<String> list = call.arguments();
            String[] permission = list.toArray(new String[list.size()]);
            PermissionUtil.requestPermission(activity, permission);
            binding.addRequestPermissionsResultListener(new Listeners.PermissionListener(activity, result));
            return;
        }

        if (call.method.equals("toSelfSetting")) {
            PermissionUtil.toSelfSetting(activity);
            return;
        }
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setBinding(ActivityPluginBinding binding) {
        this.binding = binding;
    }

}
