package cn.xinidi.sysplu;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;

import cn.xinidi.sysplu.handlers.AppInfoCallHandlerImpl;
import cn.xinidi.sysplu.handlers.PermissionCallHandlerImpl;
import cn.xinidi.sysplu.handlers.StorageCallHandlerImpl;
import cn.xinidi.sysplu.handlers.SystemCallHandlerImpl;
import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * SyspluPlugin
 */
public class SyspluPlugin implements FlutterPlugin, ActivityAware {
    //ID
    private static String PLU_PERMISSION = "cn.xinidi/permission";
    private static String PLU_APP_INFO = "cn.xinidi/appinfo";
    private static String PLU_STORAGE = "cn.xinidi/storage";
    private static String PLU_SYSTEM = "cn.xinidi/system";

    //channel
    private MethodChannel permissionChannel;
    private MethodChannel appInfoChannel;
    private MethodChannel storageChannel;
    private MethodChannel systemChannel;

    //handler
    private PermissionCallHandlerImpl permissionCallHandler;
    private AppInfoCallHandlerImpl appInfoCallHandler;
    private StorageCallHandlerImpl storageCallHandler;
    private SystemCallHandlerImpl systemCallHandler;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        startListing(binding.getBinaryMessenger());
        Log.d("GFlutter", "onAttachedToEngine");
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        Log.d("GFlutter", "onDetachedFromEngine");
        stopListing();
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        Log.d("GFlutter", "onAttachedToActivity");
        //添加权限回调监听
        Listeners.PermissionListener listener = new Listeners.PermissionListener();
        binding.addRequestPermissionsResultListener(listener);

        startListingToActivity(binding.getActivity().getApplication(), binding.getActivity(), listener);
    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        Log.d("GFlutter", "onReattachedToActivityForConfigChanges");
        onAttachedToActivity(binding);
    }

    @Override
    public void onDetachedFromActivity() {
        Log.d("GFlutter", "onDetachedFromActivity");
        stopListingToActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        Log.d("GFlutter", "onDetachedFromActivityForConfigChanges");
        onDetachedFromActivity();
    }

    public static void registerWith(Registrar registrar) {
        SyspluPlugin syspluPlugin = new SyspluPlugin();
        syspluPlugin.startListing(registrar.messenger());

        Listeners.PermissionListener listener = new Listeners.PermissionListener();
        registrar.addRequestPermissionsResultListener(listener);

        if (registrar.activeContext() instanceof Activity) {
            syspluPlugin.startListingToActivity(
                    registrar.activity().getApplication(),
                    registrar.activity(),
                    listener
            );
        }
    }

    private void startListing(BinaryMessenger messenger) {
        permissionChannel = new MethodChannel(messenger, PLU_PERMISSION);
        appInfoChannel = new MethodChannel(messenger, PLU_APP_INFO);
        storageChannel = new MethodChannel(messenger, PLU_STORAGE);
        systemChannel = new MethodChannel(messenger, PLU_SYSTEM);

        permissionCallHandler = new PermissionCallHandlerImpl();
        appInfoCallHandler = new AppInfoCallHandlerImpl();
        storageCallHandler = new StorageCallHandlerImpl();
        systemCallHandler = new SystemCallHandlerImpl();

        permissionChannel.setMethodCallHandler(permissionCallHandler);
        appInfoChannel.setMethodCallHandler(appInfoCallHandler);
        storageChannel.setMethodCallHandler(storageCallHandler);
        systemChannel.setMethodCallHandler(systemCallHandler);
    }

    private void stopListing() {
        permissionChannel.setMethodCallHandler(null);
        appInfoChannel.setMethodCallHandler(null);
        storageChannel.setMethodCallHandler(null);
        systemChannel.setMethodCallHandler(null);

        permissionCallHandler = null;
        appInfoCallHandler = null;
        storageCallHandler = null;
        systemCallHandler = null;

        permissionChannel = null;
        appInfoChannel = null;
        storageChannel = null;
        systemChannel = null;
    }

    private void startListingToActivity(Application application, Activity activity, Listeners.PermissionListener listener) {
        if (permissionCallHandler != null) {
            permissionCallHandler.setApplication(application);
            permissionCallHandler.setActivity(activity);
            permissionCallHandler.setPermissionListener(listener);
        }

        if (appInfoCallHandler != null) {
            appInfoCallHandler.setApplication(application);
            appInfoCallHandler.setActivity(activity);
        }
        if (storageCallHandler != null) {
            storageCallHandler.setApplication(application);
            storageCallHandler.setActivity(activity);
        }

        if (systemCallHandler != null) {
            systemCallHandler.setApplication(application);
            systemCallHandler.setActivity(activity);
        }
    }

    private void stopListingToActivity() {
        if (permissionCallHandler != null) {
            permissionCallHandler.setApplication(null);
            permissionCallHandler.setActivity(null);
        }

        if (appInfoCallHandler != null) {
            appInfoCallHandler.setApplication(null);
            appInfoCallHandler.setActivity(null);
        }

        if (storageCallHandler != null) {
            storageCallHandler.setApplication(null);
            storageCallHandler.setActivity(null);
        }

        if (systemCallHandler != null) {
            systemCallHandler.setApplication(null);
            systemCallHandler.setActivity(null);
        }
    }
}
