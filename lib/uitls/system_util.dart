import 'dart:io';

import 'package:flutter/services.dart';

/// 系统工具
class SystemUtil {
  static _SystemUtil _systemUtil;

  static _SystemUtil getAndroidInstance() {
    if (Platform.isAndroid) {
      if (_systemUtil == null) _systemUtil = _SystemUtil();
      return _systemUtil;
    }
    return null;
  }
}

class _SystemUtil {
  static const MethodChannel _channel = MethodChannel('cn.xinidi/system');

  //请求Root
  Future<bool> requestRoot() async {
    return await _channel.invokeMethod('requestRoot');
  }

  //是否具有Root
  Future<bool> hasRoot() async {
    return await _channel.invokeMethod('hasRoot');
  }

  ///是否具有安装未知应用的权限，AndroidO上才有判断
  Future<bool> hasInstallPermissionWithO() async {
    return await _channel.invokeMethod('hasInstallPermissionWithO');
  }

  ///跳转设置安装未知来源应用权限界面
  Future<void> startInstallPermissionSettingActivity() async {
    return await _channel.invokeMethod('startInstallPermissionSettingActivity');
  }

  ///安装apk
  ///
  /// 1.在 AndroidManifest.xml 添加权限：
  ///   <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
  ///   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  ///   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  ///
  /// 2.在 AndroidManifest.xml <application> 中添加：
  ///   <provider
  ///     android:name="androidx.core.content.FileProvider"
  ///     android:authorities="${applicationId}.fileProvider"
  ///     android:exported="false"
  ///     android:grantUriPermissions="true">
  ///     <meta-data
  ///          android:name="android.support.FILE_PROVIDER_PATHS"
  ///          android:resource="@xml/file_paths" />
  ///   </provider>
  ///
  /// 3.在 res/xml/file_paths.xml(无则建) 中添加：
  ///   <?xml version="1.0" encoding="utf-8"?>
  ///   <paths>
  ///       <external-path path="Android/data/包名/" name="files_path" />
  ///       <external-path path="." name="storage_path" />
  ///   </paths>
  Future<void> installApk(String apkPath) async {
    await _channel.invokeMethod("installApk", apkPath);
  }

  ///打开对应文件
  Future<bool> startTypeFile(String filePath) async {
    return await _channel.invokeMethod("startTypeFile", filePath);
  }
}
