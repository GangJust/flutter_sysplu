import 'package:flutter/material.dart';
import 'package:sysplu/sysplu.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    loader();
  }

  void loader() async {
    PermissionUtil.getAndroidInstance().requestPermission([
      PermissionUtil.getAndroidInstance().permission.READ_EXTERNAL_STORAGE,
      PermissionUtil.getAndroidInstance().permission.WRITE_EXTERNAL_STORAGE,
    ]).then((e) => print(e));

    /*String baseApk ="/data/app/com.tencent.tmgp.pubgmhd-rVumpzLvhiGqYxp_sUIk5g==/base.apk";
    String toPath = "/storage/emulated/0/Download/export.apk";

    AppInfoUtil.getAndroidInstance().exportApk(baseApk, toPath).then((e) {
      print("$e");
    });*/

    /*AppInfoUtil.getAndroidInstance()
        .getInstallApkInfoAll(-1)
        .then((e) => print('所有：$e'));*/

    //SystemUtil.getAndroidInstance().installApk("/storage/emulated/0/Download/1.apk");
    SystemUtil.getAndroidInstance().startTypeFile("/storage/emulated/0/Download/1.apk").then((b){
      print("f:$b");
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView.builder(
            itemCount: 200,
            itemBuilder: (_, i) {
              return Text('mmm-$i');
            }),
      ),
    );
  }
}
