import 'dart:io';

import 'package:flutter/services.dart';

///存储工具

class StorageUtil {
  static _StorageUtil _storageUtil;

  static _StorageUtil getAndroidInstance() {
    if (Platform.isAndroid) {
      if (_StorageUtil == null) _storageUtil = _StorageUtil();
      return _storageUtil;
    }
    return null;
  }
}

class _StorageUtil {
  static const MethodChannel _channel = MethodChannel('cn.xinidi/storage');

  //获得根目录/data内部存储路径
  Future<String> getDataDirectory() async {
    return await _channel.invokeMethod('getDataDirectory');
  }

  //获得缓存目录/cache
  Future<String> getCacheDirectory() async {
    return await _channel.invokeMethod('getCacheDirectory');
  }

  //获得SD卡目录/mnt/sdcard（获取的是手机外置sd卡的路径）
  Future<String> getStorageDirectory() async {
    return await _channel.invokeMethod('getStorageDirectory');
  }

  //获得系统目录/system
  Future<String> getRootDirectory() async {
    return await _channel.invokeMethod('getRootDirectory');
  }

  //用于获取APP的cache目录 /data/data/包名/cache
  Future<String> getCacheDir() async {
    return await _channel.invokeMethod('getCacheDir');
  }

  //用于获取APP的在SD卡中的cache目
  Future<String> getStorageCacheDir() async {
    return await _channel.invokeMethod('getStorageCacheDir');
  }

  //用于获取APP的cache目录 /data/data/包名/files
  Future<String> getFilesDir() async {
    return await _channel.invokeMethod('getFilesDir');
  }

  //用于获取Android的obb目录
  Future<String> getObbDir() async {
    return await _channel.invokeMethod('getObbDir');
  }

  //用于获取APP所在包目录
  Future<String> getPackageName() async {
    return await _channel.invokeMethod('getPackageName');
  }

  //用于获取当前应用程序对应的 apk 文件的路径
  Future<String> getPackageCodePath() async {
    return await _channel.invokeMethod('getPackageCodePath');
  }

  //用于获取该程序的安装包路径
  Future<String> getPackageResourcePath() async {
    return await _channel.invokeMethod('getPackageResourcePath');
  }
}
