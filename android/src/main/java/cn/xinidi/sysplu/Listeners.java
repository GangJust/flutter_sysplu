package cn.xinidi.sysplu;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

import cn.xinidi.sysplu.utils.PermissionUtil;
import io.flutter.Log;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class Listeners {
    /**
     * 权限回调监听
     */
    public static class PermissionListener implements PluginRegistry.RequestPermissionsResultListener {
        private MethodChannel.Result result;
        private Activity activity;

        public PermissionListener(Activity activity, MethodChannel.Result result) {
            this.activity = activity;
            this.result = result;
        }

        @Override
        public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            Map<String, Integer> status = new HashMap<>();
            for (int i = 0; i < permissions.length; i++) {
                boolean hasPermission = PermissionUtil.checkPermission(activity, permissions[i]);
                boolean shouldShow = PermissionUtil.shouldShowRequest(activity, permissions[i]);
                //如果被永久拒绝
                if (shouldShow == false && hasPermission == false) {
                    status.put(permissions[i], -2);
                    continue;
                }
                status.put(permissions[i], grantResults[i]);
            }
            result.success(status);
            return true;
        }
    }
}
