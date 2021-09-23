import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:ocbcsample/provider/app_theme.dart';
import 'package:ocbcsample/res/colors.dart';
import 'package:ocbcsample/utils/theme_util.dart';
import 'package:ocbcsample/widgets/beizier_path_painter.dart';
import 'package:ocbcsample/widgets/gradient_appbar.dart';
import 'package:ocbcsample/widgets/xtextfield.dart';
import 'package:provider/provider.dart';

class TransferPage extends StatelessWidget {
  const TransferPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "Make a transfer",
        theme: getTheme(Colours.appForeground),
        home: TransferState());
  }
}

class TransferState extends StatefulWidget {
  const TransferState({Key? key}) : super(key: key);

  @override
  _TransferStateState createState() => _TransferStateState();
}

class _TransferStateState extends State<TransferState> {
  double screenWidth = 0;
  bool isHidden = true;

  TextEditingController recipientController = TextEditingController();
  TextEditingController dateController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();
  TextEditingController amountController = TextEditingController();

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
                  recipientController,
                  "Recipient",
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
                  dateController,
                  "Date of transfer",
                  prefixIcon: Icons.date_range,
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
                child: XTextField(
                  descriptionController,
                  "Description",
                  prefixIcon: Icons.description,
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
                child: XTextField(
                  amountController,
                  "Amount",
                  prefixIcon: Icons.money,
                  suffixIcon: Icon(
                    Icons.close,
                    color: Theme.of(context).textTheme.button!.color,
                  ),
                  onChanged: (text) {},
                ),
              ),
              Container(
                height: 200,
                width: 400,
                child: Stack(
                  fit: StackFit.loose,
                  children: [
                    Positioned(child: GestureDetector(
                      onTap: () {
                        Navigator.of(context).push(
                          MaterialPageRoute(builder: (BuildContext context) {
                            return TransferPage();
                          }),
                        );
                      },
                      child: Text("Cancel",
                          style: TextStyle(
                              color: Colors.black,
                              fontWeight: FontWeight.bold)),
                    ), left: 60, bottom: 5,),
                    Positioned(child: GestureDetector(
                      onTap: () {
                        Navigator.of(context).push(
                          MaterialPageRoute(builder: (BuildContext context) {
                            return TransferPage();
                          }),
                        );
                      },
                      child: Text("Submit",
                          style: TextStyle(
                              color: Colors.black,
                              fontWeight: FontWeight.bold)),
                    ), right: 60, bottom: 5,)
                  ],
                ),)
            ],
          ),
        ),
      ),
    );
  }
}
