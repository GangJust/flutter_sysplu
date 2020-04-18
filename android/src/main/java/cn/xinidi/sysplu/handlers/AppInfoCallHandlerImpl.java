package cn.xinidi.sysplu.handlers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.List;
import java.util.Map;

import cn.xinidi.sysplu.utils.AppInfoUtil;
import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * apk相关
 */
public class AppInfoCallHandlerImpl implements BaseCallHandler {
    private Application application;
    private Activity activity;
    private HandlerHelper handlerHelper;

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (application == null || activity == null) {
            result.error("-1", "Failed to attach to Activity!", null);
            return;
        }

        //初始化 Handler
        if (handlerHelper == null) handlerHelper = new HandlerHelper(result);

        //取Apk图标
        if (call.method.equals("getApkIcon")) {
            String apkPath = call.argument("apkPath");
            String toPath = call.argument("toPath");
            if (toPath == null) {
                byte[] bytes = AppInfoUtil.getApkIcon(activity, apkPath);
                result.success(bytes);
                return;
            }
            String iconPath = AppInfoUtil.getApkIcon(activity, apkPath, toPath);
            result.success(iconPath);
            return;
        }

        //取已安装Apk图标
        if (call.method.equals("getInstallApkIcon")) {
            String packageName = call.argument("packageName");
            String toPath = call.argument("toPath");
            if (toPath == null) {
                byte[] bytes = AppInfoUtil.getInstallApkIcon(activity, packageName);
                result.success(bytes);
                return;
            }
            String iconPath = AppInfoUtil.getInstallApkIcon(activity, packageName, toPath);
            result.success(iconPath);
            return;
        }

        //取Apk信息
        if (call.method.equals("getApkInfo")) {
            String apk = new StringBuffer().append(call.arguments).toString();
            result.success(AppInfoUtil.getAppInfo(activity, apk));
        }

        //取已安装Apk信息
        if (call.method.equals("getInstallApkInfo")) {
            String packageName = new StringBuffer().append(call.arguments).toString();
            result.success(AppInfoUtil.getInstallApkInfo(activity, packageName));
        }

        //取已安装所有Apk信息
        if (call.method.equals("getInstallApkInfoAll")) {
            int type = Integer.parseInt(call.arguments().toString());
            handlerHelper.getInstallApkInfoAll(activity, type);
        }

        //提取Apk
        if (call.method.equals("exportApk")) {
            String baseApk = call.argument("baseApk");
            String toPath = call.argument("toPath");
            handlerHelper.exportApk(baseApk, toPath);
        }
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Handler消息辅助
     */
    private static class HandlerHelper {
        private MethodChannel.Result mResult;
        private Handler handler;
        //所有已安装的应用列表
        private static List<Map<String, Object>> installs;

        public HandlerHelper(MethodChannel.Result result) {
            this.mResult = result;

            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1000) {
                        mResult.success(msg.getData().getString("path"));
                    } else if (msg.what == 1001) {
                        mResult.success(installs);
                    }
                }
            };
        }

        /**
         * 提取Apk(耗时操作)
         *
         * @param baseApk
         * @param toPath
         */
        void exportApk(final String baseApk, final String toPath) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String path = AppInfoUtil.exportApk(baseApk, toPath);
                    //数据
                    Bundle bundle = new Bundle();
                    bundle.putString("path", path);

                    //消息
                    Message message = new Message();
                    message.what = 1000;
                    message.setData(bundle);

                    //发送
                    handler.sendMessage(message);
                }
            }).start();
        }

        /**
         * 取所有安装安装App(耗时操作)
         *
         * @param type
         */
        void getInstallApkInfoAll(final Context context, final int type) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    installs = AppInfoUtil.installAppAll(context, type);
                    //数据

                    //消息
                    Message message = new Message();
                    message.what = 1001;

                    //发送
                    handler.sendMessage(message);
                }
            }).start();
        }
    }
}
