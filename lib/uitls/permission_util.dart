import 'dart:io';

import 'package:flutter/services.dart';

///常量
class _AndroidPermissionGroup {
  _AndroidPermissionGroup._();

  final String CALENDAR = "android.permission-group.CALENDAR";
  final String CALL_LOG = "android.permission-group.CALL_LOG";
  final String CAMERA = "android.permission-group.CAMERA";
  final String CONTACTS = "android.permission-group.CONTACTS";
  final String LOCATION = "android.permission-group.LOCATION";
  final String MICROPHONE = "android.permission-group.MICROPHONE";
  final String PHONE = "android.permission-group.PHONE";
  final String SENSORS = "android.permission-group.SENSORS";
  final String SMS = "android.permission-group.SMS";
  final String STORAGE = "android.permission-group.STORAGE";
}

///常量
class _AndroidPermission {
  _AndroidPermission._();

  final String ACCEPT_HANDOVER = "android.permission.ACCEPT_HANDOVER";
  final String ACCESS_CHECKIN_PROPERTIES =
      "android.permission.ACCESS_CHECKIN_PROPERTIES";
  final String ACCESS_COARSE_LOCATION =
      "android.permission.ACCESS_COARSE_LOCATION";
  final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
  final String ACCESS_LOCATION_EXTRA_COMMANDS =
      "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS";
  final String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";
  final String ACCESS_NOTIFICATION_POLICY =
      "android.permission.ACCESS_NOTIFICATION_POLICY";
  final String ACCESS_WIFI_STATE = "android.permission.ACCESS_WIFI_STATE";
  final String ACCOUNT_MANAGER = "android.permission.ACCOUNT_MANAGER";
  final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
  final String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";
  final String BATTERY_STATS = "android.permission.BATTERY_STATS";
  final String BIND_ACCESSIBILITY_SERVICE =
      "android.permission.BIND_ACCESSIBILITY_SERVICE";
  final String BIND_APPWIDGET = "android.permission.BIND_APPWIDGET";
  final String BIND_AUTOFILL_SERVICE =
      "android.permission.BIND_AUTOFILL_SERVICE";

  /// @deprecated
  final String BIND_CARRIER_MESSAGING_SERVICE =
      "android.permission.BIND_CARRIER_MESSAGING_SERVICE";
  final String BIND_CARRIER_SERVICES =
      "android.permission.BIND_CARRIER_SERVICES";
  final String BIND_CHOOSER_TARGET_SERVICE =
      "android.permission.BIND_CHOOSER_TARGET_SERVICE";
  final String BIND_CONDITION_PROVIDER_SERVICE =
      "android.permission.BIND_CONDITION_PROVIDER_SERVICE";
  final String BIND_DEVICE_ADMIN = "android.permission.BIND_DEVICE_ADMIN";
  final String BIND_DREAM_SERVICE = "android.permission.BIND_DREAM_SERVICE";
  final String BIND_INCALL_SERVICE = "android.permission.BIND_INCALL_SERVICE";
  final String BIND_INPUT_METHOD = "android.permission.BIND_INPUT_METHOD";
  final String BIND_MIDI_DEVICE_SERVICE =
      "android.permission.BIND_MIDI_DEVICE_SERVICE";
  final String BIND_NFC_SERVICE = "android.permission.BIND_NFC_SERVICE";
  final String BIND_NOTIFICATION_LISTENER_SERVICE =
      "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE";
  final String BIND_PRINT_SERVICE = "android.permission.BIND_PRINT_SERVICE";
  final String BIND_QUICK_SETTINGS_TILE =
      "android.permission.BIND_QUICK_SETTINGS_TILE";
  final String BIND_REMOTEVIEWS = "android.permission.BIND_REMOTEVIEWS";
  final String BIND_SCREENING_SERVICE =
      "android.permission.BIND_SCREENING_SERVICE";
  final String BIND_TELECOM_CONNECTION_SERVICE =
      "android.permission.BIND_TELECOM_CONNECTION_SERVICE";
  final String BIND_TEXT_SERVICE = "android.permission.BIND_TEXT_SERVICE";
  final String BIND_TV_INPUT = "android.permission.BIND_TV_INPUT";
  final String BIND_VISUAL_VOICEMAIL_SERVICE =
      "android.permission.BIND_VISUAL_VOICEMAIL_SERVICE";
  final String BIND_VOICE_INTERACTION =
      "android.permission.BIND_VOICE_INTERACTION";
  final String BIND_VPN_SERVICE = "android.permission.BIND_VPN_SERVICE";
  final String BIND_VR_LISTENER_SERVICE =
      "android.permission.BIND_VR_LISTENER_SERVICE";
  final String BIND_WALLPAPER = "android.permission.BIND_WALLPAPER";
  final String BLUETOOTH = "android.permission.BLUETOOTH";
  final String BLUETOOTH_ADMIN = "android.permission.BLUETOOTH_ADMIN";
  final String BLUETOOTH_PRIVILEGED = "android.permission.BLUETOOTH_PRIVILEGED";
  final String BODY_SENSORS = "android.permission.BODY_SENSORS";
  final String BROADCAST_PACKAGE_REMOVED =
      "android.permission.BROADCAST_PACKAGE_REMOVED";
  final String BROADCAST_SMS = "android.permission.BROADCAST_SMS";
  final String BROADCAST_STICKY = "android.permission.BROADCAST_STICKY";
  final String BROADCAST_WAP_PUSH = "android.permission.BROADCAST_WAP_PUSH";
  final String CALL_PHONE = "android.permission.CALL_PHONE";
  final String CALL_PRIVILEGED = "android.permission.CALL_PRIVILEGED";
  final String CAMERA = "android.permission.CAMERA";
  final String CAPTURE_AUDIO_OUTPUT = "android.permission.CAPTURE_AUDIO_OUTPUT";
  final String CAPTURE_SECURE_VIDEO_OUTPUT =
      "android.permission.CAPTURE_SECURE_VIDEO_OUTPUT";
  final String CAPTURE_VIDEO_OUTPUT = "android.permission.CAPTURE_VIDEO_OUTPUT";
  final String CHANGE_COMPONENT_ENABLED_STATE =
      "android.permission.CHANGE_COMPONENT_ENABLED_STATE";
  final String CHANGE_CONFIGURATION = "android.permission.CHANGE_CONFIGURATION";
  final String CHANGE_NETWORK_STATE = "android.permission.CHANGE_NETWORK_STATE";
  final String CHANGE_WIFI_MULTICAST_STATE =
      "android.permission.CHANGE_WIFI_MULTICAST_STATE";
  final String CHANGE_WIFI_STATE = "android.permission.CHANGE_WIFI_STATE";
  final String CLEAR_APP_CACHE = "android.permission.CLEAR_APP_CACHE";
  final String CONTROL_LOCATION_UPDATES =
      "android.permission.CONTROL_LOCATION_UPDATES";
  final String DELETE_CACHE_FILES = "android.permission.DELETE_CACHE_FILES";
  final String DELETE_PACKAGES = "android.permission.DELETE_PACKAGES";
  final String DIAGNOSTIC = "android.permission.DIAGNOSTIC";
  final String DISABLE_KEYGUARD = "android.permission.DISABLE_KEYGUARD";
  final String DUMP = "android.permission.DUMP";
  final String EXPAND_STATUS_BAR = "android.permission.EXPAND_STATUS_BAR";
  final String FACTORY_TEST = "android.permission.FACTORY_TEST";
  final String FOREGROUND_SERVICE = "android.permission.FOREGROUND_SERVICE";
  final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
  final String GET_ACCOUNTS_PRIVILEGED =
      "android.permission.GET_ACCOUNTS_PRIVILEGED";
  final String GET_PACKAGE_SIZE = "android.permission.GET_PACKAGE_SIZE";

  /// @deprecated
  final String GET_TASKS = "android.permission.GET_TASKS";
  final String GLOBAL_SEARCH = "android.permission.GLOBAL_SEARCH";
  final String INSTALL_LOCATION_PROVIDER =
      "android.permission.INSTALL_LOCATION_PROVIDER";
  final String INSTALL_PACKAGES = "android.permission.INSTALL_PACKAGES";
  final String INSTALL_SHORTCUT =
      "com.android.launcher.permission.INSTALL_SHORTCUT";
  final String INSTANT_APP_FOREGROUND_SERVICE =
      "android.permission.INSTANT_APP_FOREGROUND_SERVICE";
  final String INTERNET = "android.permission.INTERNET";
  final String KILL_BACKGROUND_PROCESSES =
      "android.permission.KILL_BACKGROUND_PROCESSES";
  final String LOCATION_HARDWARE = "android.permission.LOCATION_HARDWARE";
  final String MANAGE_DOCUMENTS = "android.permission.MANAGE_DOCUMENTS";
  final String MANAGE_OWN_CALLS = "android.permission.MANAGE_OWN_CALLS";
  final String MASTER_CLEAR = "android.permission.MASTER_CLEAR";
  final String MEDIA_CONTENT_CONTROL =
      "android.permission.MEDIA_CONTENT_CONTROL";
  final String MODIFY_AUDIO_SETTINGS =
      "android.permission.MODIFY_AUDIO_SETTINGS";
  final String MODIFY_PHONE_STATE = "android.permission.MODIFY_PHONE_STATE";
  final String MOUNT_FORMAT_FILESYSTEMS =
      "android.permission.MOUNT_FORMAT_FILESYSTEMS";
  final String MOUNT_UNMOUNT_FILESYSTEMS =
      "android.permission.MOUNT_UNMOUNT_FILESYSTEMS";
  final String NFC = "android.permission.NFC";
  final String NFC_TRANSACTION_EVENT =
      "android.permission.NFC_TRANSACTION_EVENT";
  final String PACKAGE_USAGE_STATS = "android.permission.PACKAGE_USAGE_STATS";

  /// @deprecated
  final String PERSISTENT_ACTIVITY = "android.permission.PERSISTENT_ACTIVITY";
  final String PROCESS_OUTGOING_CALLS =
      "android.permission.PROCESS_OUTGOING_CALLS";
  final String READ_CALENDAR = "android.permission.READ_CALENDAR";
  final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
  final String READ_CONTACTS = "android.permission.READ_CONTACTS";
  final String READ_EXTERNAL_STORAGE =
      "android.permission.READ_EXTERNAL_STORAGE";
  final String READ_FRAME_BUFFER = "android.permission.READ_FRAME_BUFFER";

  /// @deprecated
  final String READ_INPUT_STATE = "android.permission.READ_INPUT_STATE";
  final String READ_LOGS = "android.permission.READ_LOGS";
  final String READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS";
  final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
  final String READ_SMS = "android.permission.READ_SMS";
  final String READ_SYNC_SETTINGS = "android.permission.READ_SYNC_SETTINGS";
  final String READ_SYNC_STATS = "android.permission.READ_SYNC_STATS";
  final String READ_VOICEMAIL =
      "com.android.voicemail.permission.READ_VOICEMAIL";
  final String REBOOT = "android.permission.REBOOT";
  final String RECEIVE_BOOT_COMPLETED =
      "android.permission.RECEIVE_BOOT_COMPLETED";
  final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
  final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
  final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
  final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
  final String REORDER_TASKS = "android.permission.REORDER_TASKS";
  final String REQUEST_COMPANION_RUN_IN_BACKGROUND =
      "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND";
  final String REQUEST_COMPANION_USE_DATA_IN_BACKGROUND =
      "android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND";
  final String REQUEST_DELETE_PACKAGES =
      "android.permission.REQUEST_DELETE_PACKAGES";
  final String REQUEST_IGNORE_BATTERY_OPTIMIZATIONS =
      "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";
  final String REQUEST_INSTALL_PACKAGES =
      "android.permission.REQUEST_INSTALL_PACKAGES";

  /// @deprecated
  final String RESTART_PACKAGES = "android.permission.RESTART_PACKAGES";
  final String SEND_RESPOND_VIA_MESSAGE =
      "android.permission.SEND_RESPOND_VIA_MESSAGE";
  final String SEND_SMS = "android.permission.SEND_SMS";
  final String SET_ALARM = "com.android.alarm.permission.SET_ALARM";
  final String SET_ALWAYS_FINISH = "android.permission.SET_ALWAYS_FINISH";
  final String SET_ANIMATION_SCALE = "android.permission.SET_ANIMATION_SCALE";
  final String SET_DEBUG_APP = "android.permission.SET_DEBUG_APP";

  /// @deprecated
  final String SET_PREFERRED_APPLICATIONS =
      "android.permission.SET_PREFERRED_APPLICATIONS";
  final String SET_PROCESS_LIMIT = "android.permission.SET_PROCESS_LIMIT";
  final String SET_TIME = "android.permission.SET_TIME";
  final String SET_TIME_ZONE = "android.permission.SET_TIME_ZONE";
  final String SET_WALLPAPER = "android.permission.SET_WALLPAPER";
  final String SET_WALLPAPER_HINTS = "android.permission.SET_WALLPAPER_HINTS";
  final String SIGNAL_PERSISTENT_PROCESSES =
      "android.permission.SIGNAL_PERSISTENT_PROCESSES";
  final String STATUS_BAR = "android.permission.STATUS_BAR";
  final String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW";
  final String TRANSMIT_IR = "android.permission.TRANSMIT_IR";
  final String UNINSTALL_SHORTCUT =
      "com.android.launcher.permission.UNINSTALL_SHORTCUT";
  final String UPDATE_DEVICE_STATS = "android.permission.UPDATE_DEVICE_STATS";
  final String USE_BIOMETRIC = "android.permission.USE_BIOMETRIC";

  /// @deprecated
  final String USE_FINGERPRINT = "android.permission.USE_FINGERPRINT";
  final String USE_SIP = "android.permission.USE_SIP";
  final String VIBRATE = "android.permission.VIBRATE";
  final String WAKE_LOCK = "android.permission.WAKE_LOCK";
  final String WRITE_APN_SETTINGS = "android.permission.WRITE_APN_SETTINGS";
  final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
  final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
  final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
  final String WRITE_EXTERNAL_STORAGE =
      "android.permission.WRITE_EXTERNAL_STORAGE";
  final String WRITE_GSERVICES = "android.permission.WRITE_GSERVICES";
  final String WRITE_SECURE_SETTINGS =
      "android.permission.WRITE_SECURE_SETTINGS";
  final String WRITE_SETTINGS = "android.permission.WRITE_SETTINGS";
  final String WRITE_SYNC_SETTINGS = "android.permission.WRITE_SYNC_SETTINGS";
  final String WRITE_VOICEMAIL =
      "com.android.voicemail.permission.WRITE_VOICEMAIL";
}

///状态
///GRANTED  拥有权限
///DENIED  未拥有权限
///DEADLY  拒绝了权限
enum PermissionStatus { GRANTED, DENIED, DEADLY }

///权限工具
class PermissionUtil {
  static _AndroidPermissionUtil _androidPermissionUtil;

  static _AndroidPermissionUtil getAndroidInstance() {
    if (Platform.isAndroid) {
      if (_androidPermissionUtil == null)
        _androidPermissionUtil = _AndroidPermissionUtil();
      return _androidPermissionUtil;
    }
    return null;
  }
}

class _AndroidPermissionUtil {
  static const MethodChannel _channel = const MethodChannel(
      'cn.xinidi/permission');

  _AndroidPermissionGroup permissionGroup = _AndroidPermissionGroup._();
  _AndroidPermission permission = _AndroidPermission._();

  ///检查是否具有权限
  Future<PermissionStatus> checkPermission(String permission) async {
    bool b = await _channel.invokeMethod('checkPermission', permission);
    if (b) return PermissionStatus.GRANTED;
    //如果在处于 DEADLY 状态下使用 checkPermission 也会返回 DENIED;
    return PermissionStatus.DENIED;
  }

  ///申请目标权限
  Future<Map<String, PermissionStatus>> requestPermission(List<String> permissions) async {
    Map maps = await _channel.invokeMethod('requestPermission', permissions);
    Map<String, PermissionStatus> status = new Map();

    maps.forEach((k, v) {
      if (v == 0)
        status[k] = PermissionStatus.GRANTED;
      else if (v == -1)
        status[k] = PermissionStatus.DENIED;
      else
        status[k] = PermissionStatus.DEADLY;
    });
    return status;
  }

  ///跳转至本应用详情页
  void toSelfSetting() async {
    await _channel.invokeMethod('toSelfSetting');
  }
}