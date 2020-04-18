package cn.xinidi.sysplu.handlers;

import android.app.Activity;
import android.app.Application;

import io.flutter.plugin.common.MethodChannel;

public interface BaseCallHandler extends MethodChannel.MethodCallHandler {

    public void setApplication(Application application);

    public void setActivity(Activity activity);

}
