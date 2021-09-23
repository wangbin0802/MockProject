import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:ocbcsample/manager/userinfo_manager.dart';
import 'package:ocbcsample/pages/detail_page.dart';
import 'package:ocbcsample/provider/app_theme.dart';
import 'package:ocbcsample/provider/dark_mode.dart';
import 'package:ocbcsample/res/colors.dart';
import 'package:ocbcsample/res/theme_colors.dart';
import 'package:ocbcsample/utils/theme_util.dart';
import 'package:ocbcsample/widgets/beizier_path_painter.dart';
import 'package:ocbcsample/widgets/xtextfield.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'http/api.dart';
import 'http/http.dart';
import 'widgets/gradient_appbar.dart';

void main() {
  final appTheme = AppTheme();
  runApp(MultiProvider(
      providers: [ChangeNotifierProvider.value(value: appTheme)],
      child: const MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: getTheme(Colours.appForeground),
      home: MyHomePage(title: 'Simple'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  double screenWidth = 0;
  bool isHidden = true;
  TextEditingController usernameController = TextEditingController();
  TextEditingController pwdController = TextEditingController();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    Provider.of<AppTheme>(context).updateThemeColor(Colours.appThemeColor);
    return WillPopScope(
      onWillPop: () async {
        FocusScope.of(context).unfocus();
        return Future.value(true);
      },
      child: Scaffold(
        appBar: GradientAppBar(
          leading: GestureDetector(
            child: const Icon(
              Icons.close,
            ),
            onTap: () {
              Navigator.pop(context);
            },
          ),
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              Consumer<AppTheme>(
                builder: (BuildContext context, AppTheme appTheme, child) {
                  return CustomPaint(
                    size: Size(screenWidth, 150),
                    painter: BezierPathPainter(appTheme.themeColor),
                  );
                },
              ),
              Container(
                margin: const EdgeInsets.only(
                  top: 20,
                ),
                padding: const EdgeInsets.only(
                  left: 20,
                  right: 20,
                ),
                child: XTextField(
                  usernameController,
                  "Username",
                  prefixIcon: Icons.person,
                  obscureText: false,
                  suffixIcon: Icon(
                    Icons.close,
                    color: Theme.of(context).textTheme.button!.color,
                  ),
                  onChanged: (text) {},
                ),
              ),
              Container(
                margin: const EdgeInsets.only(
                  top: 20,
                ),
                padding: const EdgeInsets.only(
                  left: 20,
                  right: 20,
                ),
                child: XTextField(
                  pwdController,
                  "Password",
                  prefixIcon: Icons.lock,
                  suffixIcon: Icon(
                    Icons.close,
                    color: Theme.of(context).textTheme.button!.color,
                  ),
                  onChanged: (text) {},
                ),
              ),
              Container(
                margin: const EdgeInsets.only(
                  top: 30,
                ),
                padding: const EdgeInsets.only(
                  left: 20,
                  right: 20,
                ),
                child: Row(
                  children: <Widget>[
                    Expanded(
                      flex: 1,
                      child: Consumer<AppTheme>(
                        builder:
                            (BuildContext context, AppTheme appTheme, child) {
                          return MaterialButton(
                            elevation: 0,
                            onPressed: () {
                              login();
                            },
                            shape: const RoundedRectangleBorder(
                              borderRadius: BorderRadius.all(
                                Radius.circular(5),
                              ),
                            ),
                            height: 46,
                            color: appTheme.themeColor,
                            child: const Text(
                              "Login",
                              style: TextStyle(
                                color: Colors.black,
                                fontSize: 16,
                              ),
                            ),
                          );
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void login() async {
    String username = usernameController.text;
    String password = pwdController.text;
    if (username.isEmpty) {
      Fluttertoast.showToast(msg: "Please input user name");
      return;
    }
    if (password.isEmpty) {
      Fluttertoast.showToast(msg: "Please input password");
      return;
    }

    var data = {"username": username, "password": password};
    // var result = await HttpClient.getInstance()
    //     .post(Api.LOGIN, data: {"username": username, "password": password});
    var result = await get(data);
    if (result["status"] as String == "success") {
      Fluttertoast.showToast(msg: "Login success.");
      var token = result["token"] as String;
      print(token);
      UserInfoManager.getInstance().setUserToken(token);
      Navigator.push(context, MaterialPageRoute(builder: (BuildContext context) {
        return const DetailContainer();
      }));
    } else {
      Fluttertoast.showToast(msg: "Login fail.");
    }
  }

  dynamic get(Map data) async {
    var url = 'http://192.168.1.15:8080/authenticate/login';
    var httpClient = HttpClient();

    var result;
    try {
      var request = await httpClient.postUrl(Uri.parse(url));
      request.headers.add("Content-Type", "application/json");
      request.add(utf8.encode(json.encode(data)));

      var response = await request.close();
      if (response.statusCode == HttpStatus.OK) {
        var json = await response.transform(utf8.decoder).join();
        print(json);
        result = jsonDecode(json);
        print(result["token"]);
      }
    } catch (exception) {
      print("error");
    }
    return result;
  }

  /// 查询主题色
  queryThemeColor() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    int themeColorIndex = sp.getInt("themeColorIndex") ?? 0;
    return themeColorIndex;
  }

  /// 查询暗黑模式
  queryDark() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    bool isDark = sp.getBool("dark") ?? false;
    return isDark;
  }
}
