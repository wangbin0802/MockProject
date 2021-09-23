import 'dart:convert';
import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/rendering.dart';
import 'package:ocbcsample/http/api.dart';
import 'package:ocbcsample/manager/userinfo_manager.dart';
import 'package:ocbcsample/pages/transfer_page.dart';
import 'package:ocbcsample/provider/app_theme.dart';
import 'package:ocbcsample/res/colors.dart';
import 'package:ocbcsample/utils/theme_util.dart';
import 'package:ocbcsample/widgets/beizier_path_painter.dart';
import 'package:ocbcsample/widgets/gradient_appbar.dart';
import 'package:provider/provider.dart';

class DetailPage extends StatelessWidget {
  const DetailPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "Detail",
        theme: getTheme(Colours.appForeground),
        home: const DetailContainer());
  }
}

class DetailContainer extends StatefulWidget {
  const DetailContainer({Key? key}) : super(key: key);

  @override
  _DetailContainerState createState() => _DetailContainerState();
}

class _DetailContainerState extends State<DetailContainer> {
  double screenWidth = 0;
  String? balance;
  List<String> litems = ["one", "two", "three"];

  _DetailContainerState() {
    HttpClient client = HttpClient();
    client
        .getUrl(Uri.parse("${Api.BASE_URL}${Api.BALANCE}"))
        .then((HttpClientRequest request) {
      // Optionally set up headers...
      request.headers.add("Accept", "application/json");
      request.headers.add("Content-Type", "application/json");
      request.headers.add("Authorization", UserInfoManager.getInstance().getUserToken()!);
      // Optionally write to the request object...
      // Then call close.
      return request.close();
    }).then((HttpClientResponse response) async {
      // Process the response.
      // 通过 utf8 转码，jsonDecode 将数据转成json格式
      var json = await response.transform(utf8.decoder).join();
      setState(() {
        print("result:$json");
        var result = jsonDecode(json);
        if (result["status"] == "success") {
          balance = result["balance"].toString();
        }
      });
    }).catchError((onError) {
      print(onError.toString());
    });
  }

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
                decoration: BoxDecoration(
                  color: Colours.darkAppText,
                ),
                child: Column(
                  children: <Widget>[
                    Container(
                      margin: const EdgeInsets.only(
                        top: 20,
                      ),
                      padding: const EdgeInsets.only(
                        left: 20,
                        right: 20,
                      ),
                      child: const Text("You have",
                          style: TextStyle(color: Colors.black)),
                    ),
                    Container(
                      margin: const EdgeInsets.only(
                        top: 20,
                      ),
                      padding: const EdgeInsets.only(
                        left: 20,
                        right: 20,
                      ),
                      child: Text("SGD $balance",
                          style: TextStyle(
                              color: Colors.black,
                              fontWeight: FontWeight.bold)),
                    ),
                    Container(
                      margin: const EdgeInsets.only(
                        top: 20,
                        bottom: 20,
                      ),
                      padding: const EdgeInsets.only(
                        left: 20,
                        right: 20,
                      ),
                      child: Text("in your account",
                          style: TextStyle(color: Colors.black)),
                    ),
                  ],
                ),
              ),
              Container(
                height: 25.0,
              ),
              Divider(
                height: 1.0,
                indent: 10.0,
                color: Colours.darkDialogBackground,
              ),
              Container(
                height: 25.0,
              ),
              Text("Your activity",
                  style: TextStyle(
                      fontWeight: FontWeight.bold, color: Colors.black)),
              Container(
                height: 25.0,
              ),
              // Expanded(child: TransactionList())

              Container(
                height: 250,
                child: Stack(
                alignment: Alignment.bottomCenter,
                children: [
                  Positioned(child: GestureDetector(
                    onTap: () {
                      Navigator.of(context).push(
                        MaterialPageRoute(builder: (BuildContext context) {
                          return TransferPage();
                        }),
                      );
                    },
                    child: Text("Make a transfer",
                        style: TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold)),
                  ))
                ],
              ),)
            ],
          ),
        ),
      ),
    );
  }
}

class TransactionList extends StatefulWidget {
  const TransactionList({Key? key}) : super(key: key);

  @override
  _TransactionListState createState() => _TransactionListState();
}

class _TransactionListState extends State<TransactionList> {
  List<String> litems = ["one", "two", "three"];

  @override
  Widget build(BuildContext context) {
    Widget divider1 = Divider(
      color: Colours.darkDialogBackground,
    );
    return Container(
      width: double.infinity,
      height: 100,
      child: ListView.separated(
        itemCount: litems.length,
        //列表项构造器
        itemBuilder: (BuildContext context, int index) {
          return Container(
            margin: EdgeInsets.only(
              left: 40,
              right: 40,
            ),
            child: Row(
              children: <Widget>[
                Container(
                  width: 20,
                ),
                Text(litems[index],
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.black)),
                Container(
                  width: 60,
                ),
                Text(litems[index], style: TextStyle(color: Colors.black)),
                Text(litems[index],
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: Colors.black)),
              ],
            ),
          );
        },
        //分割器构造器
        separatorBuilder: (BuildContext context, int index) {
          return divider1;
        },
      ),
    );
  }
}
