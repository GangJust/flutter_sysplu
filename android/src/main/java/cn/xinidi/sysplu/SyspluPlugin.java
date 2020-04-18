package cn.xinidi.sysplu;

import androidx.annotation.NonNull;

import cn.xinidi.sysplu.handlers.AppInfoCallHandlerImpl;
import cn.xinidi.sysplu.handlers.PermissionCallHandlerImpl;
import cn.xinidi.sysplu.handlers.StorageCallHandlerImpl;
import cn.xinidi.sysplu.handlers.SystemCallHandlerImpl;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
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

    //handler
    private static PermissionCallHandlerImpl permissionCallHandler;
    private static AppInfoCallHandlerImpl appInfoCallHandler;
    private static StorageCallHandlerImpl storageCallHandler;
    private static SystemCallHandlerImpl systemCallHandler;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel permissionChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), PLU_PERMISSION);
        final MethodChannel appInfoChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), PLU_APP_INFO);
        final MethodChannel storageChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), PLU_STORAGE);
        final MethodChannel systemChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), PLU_SYSTEM);

        permissionCallHandler = new PermissionCallHandlerImpl();
        appInfoCallHandler = new AppInfoCallHandlerImpl();
        storageCallHandler = new StorageCallHandlerImpl();
        systemCallHandler = new SystemCallHandlerImpl();

        permissionChannel.setMethodCallHandler(permissionCallHandler);
        appInfoChannel.setMethodCallHandler(appInfoCallHandler);
        storageChannel.setMethodCallHandler(storageCallHandler);
        systemChannel.setMethodCallHandler(systemCallHandler);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {

    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        if (permissionCallHandler != null) {
            permissionCallHandler.setApplication(binding.getActivity().getApplication());
            permissionCallHandler.setActivity(binding.getActivity());
            permissionCallHandler.setBinding(binding);
        }

        if (appInfoCallHandler != null) {
            appInfoCallHandler.setApplication(binding.getActivity().getApplication());
            appInfoCallHandler.setActivity(binding.getActivity());
        }
        if (storageCallHandler != null) {
            storageCallHandler.setApplication(binding.getActivity().getApplication());
            storageCallHandler.setActivity(binding.getActivity());
        }

        if (systemCallHandler != null) {
            systemCallHandler.setApplication(binding.getActivity().getApplication());
            systemCallHandler.setActivity(binding.getActivity());
        }
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        onAttachedToActivity(binding);
    }

    @Override
    public void onDetachedFromActivity() {
        if (permissionCallHandler != null) {
            permissionCallHandler.setApplication(null);
            permissionCallHandler.setActivity(null);
            permissionCallHandler.setBinding(null);
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


    public static void registerWith(Registrar registrar) {
        final MethodChannel permissionChannel = new MethodChannel(registrar.messenger(), PLU_PERMISSION);
        permissionCallHandler = new PermissionCallHandlerImpl();
        permissionChannel.setMethodCallHandler(permissionCallHandler);
    }
}
