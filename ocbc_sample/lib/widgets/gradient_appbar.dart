import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:ocbcsample/provider/app_theme.dart';
import 'package:provider/provider.dart';

class GradientAppBar extends StatefulWidget implements PreferredSizeWidget {
  final Widget? leading;
  final double height;
  final List<Color> colors;
  final Widget? title;
  final bool centerTitle;
  final List<Widget>? actions;
  final Brightness brightness;

  GradientAppBar({
    Key? key,
    this.leading,
    this.height = 50,
    this.colors = const [Colors.red, Colors.redAccent],
    this.title,
    this.centerTitle = false,
    this.actions,
    this.brightness = Brightness.dark,
  }) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return GradientAppBarState();
  }

  @override
  Size get preferredSize => Size.fromHeight(height);
}

class GradientAppBarState extends State<GradientAppBar> {
  @override
  Widget build(BuildContext context) {
    return PreferredSize(
      preferredSize: widget.preferredSize,
      child: Stack(
        children: <Widget>[
          Consumer<AppTheme>(
            builder: (context, appTheme, child) {
              return Container(
                child: AppBar(
                  brightness: widget.brightness,
                  leading: widget.leading,
                  titleSpacing: 0,
                  title: widget.title,
                  backgroundColor: Colors.transparent,
                  elevation: 0,
                  centerTitle: widget.centerTitle,
                  actions: widget.actions,
                ),
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                    begin: Alignment.topLeft,
                    end: Alignment.bottomRight,
                    colors: [
                      appTheme.themeColor,
                      appTheme.themeColor,
                    ],
                  ),
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}
