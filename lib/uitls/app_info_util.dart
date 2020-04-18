import 'dart:io';
import 'dart:isolate';
import 'dart:typed_data';

import 'package:flutter/services.dart';

///app信息

class AppInfoUtil {
  static _AppInfoUtil _appInfoUtil;

  AppInfoUtil._();

  static _AppInfoUtil getAndroidInstance() {
    if (Platform.isAndroid) {
      if (_appInfoUtil == null) _appInfoUtil = new _AppInfoUtil();
      return _appInfoUtil;
    }
    ;
    return null;
  }
}

class _AppInfoUtil {
  static const MethodChannel _channel = MethodChannel('cn.xinidi/appinfo');

  ///取apk图标
  /// 在 AndroidManifest.xml 添加权限：
  ///   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  ///   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  ///返回参数类型将由 [toPath] 决定,如果toPath不为空,则应该传入一个正确的路径(包含文件名),
  ///否则,返回 [Uint8List] 数据
  Future getApkIcon(String apkPath, [String toPath]) async {
    if (toPath == null) {
      return await _channel.invokeMethod("getApkIcon", {"apkPath": apkPath});
    }
    return await _channel.invokeMethod("getApkIcon", {"apkPath": apkPath, "toPath": toPath});
  }

  ///取已安装apk图标
  /// 在 AndroidManifest.xml 添加权限：
  ///   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  ///   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  ///返回参数类型将由 [toPath] 决定,如果toPath不为空,则应该传入一个正确的路径(包含文件名),
  ///否则,返回 [Uint8List] 数据
  Future getInstallApkIcon(String packageName, [String toPath]) async {
    if (toPath == null) {
      return await _channel.invokeMethod("getInstallApkIcon", {"packageName": packageName});
    }
    return await _channel.invokeMethod("getInstallApkIcon", {"packageName": packageName, "toPath": toPath});
  }

  ///取apk信息
  Future<Map> getApkInfo(String apkPath) async {
    return await _channel.invokeMethod('getApkInfo', apkPath);
  }

  ///取已安装apk信息
  Future<Map> getInstallApkInfo(String packageName) async {
    return await _channel.invokeMethod('getInstallApkInfo', packageName);
  }

  ///取已安装所有apk信息
  ///type 0 用户apk
  ///type 1 系统apk
  ///type -1 所有apk
  Future<List<dynamic>> getInstallApkInfoAll(int type) async {
    return await _channel.invokeMethod('getInstallApkInfoAll', type);
  }

  /// 提取Apk
  /// 在 AndroidManifest.xml 添加权限：
  ///   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  ///   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  Future<String> exportApk(String baseApk, String toPath) async{
    return await _channel.invokeMethod('exportApk', {'baseApk': baseApk, "toPath": toPath});
  }
}
