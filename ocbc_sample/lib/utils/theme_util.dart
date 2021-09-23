import 'package:flutter/material.dart';
import 'package:ocbcsample/res/colors.dart';

getTheme(Color themeColor, {bool isDarkMode = false}) {
  return ThemeData(
    // 页面背景颜色
    scaffoldBackgroundColor:
    isDarkMode ? Colours.darkAppBackground : Colours.appBackground,
    accentColor: isDarkMode ? Colours.darkAppSubText : Colours.appSubText,
    // tab 指示器颜色
    indicatorColor: Colors.white,
    backgroundColor:
    isDarkMode ? Colours.darkAppForeground : Colours.appForeground,
    // 底部菜单背景颜色
    bottomAppBarColor:
    isDarkMode ? Colours.darkAppForeground : Colours.appForeground,
    primaryColor: Colours.appThemeColor,
    primaryColorDark: Colours.appBackground,
//      brightness: isDarkMode ? Brightness.light : Brightness.dark,
    ///  appBar theme
    appBarTheme: AppBarTheme(
      color: Colors.yellow,
      // 状态栏字体颜色
      brightness: Brightness.dark,
      iconTheme: IconThemeData(color: Colors.white),
      actionsIconTheme: IconThemeData(
        color: Colors.white,
      ),
    ),
    textTheme: TextTheme(
      // 一级文本
      bodyText1: isDarkMode
          ? TextStyle(
        color: Colours.darkAppText,
      )
          : TextStyle(
        color: Colours.appText,
      ),
//        subtitle: isDarkMode
//            ? TextStyle(color: Colors.amber)
//            : TextStyle(color: Colors.cyan),
      // 二级文本
      bodyText2: isDarkMode
          ? TextStyle(
        color: Colours.darkAppSubText,
        fontSize: 14,
      )
          : TextStyle(
        color: Colours.appSubText,
        fontSize: 14,
      ),
      caption: isDarkMode
          ? TextStyle(color: Colours.darkAppActionClip)
          : TextStyle(color: Colours.appActionClip),
      button: TextStyle(color: isDarkMode ? Colors.white30 : Colors.black54),
    ),
    inputDecorationTheme: InputDecorationTheme(
      enabledBorder: UnderlineInputBorder(
        borderSide: BorderSide(
          color: isDarkMode ? Colours.darkAppDivider : Colours.appDivider,
          width: 1,
          style: BorderStyle.solid,
        ),
      ),
      border: UnderlineInputBorder(
        borderSide: BorderSide(
          color: isDarkMode ? Colours.darkAppDivider : Colours.appDivider,
          width: 1,
          style: BorderStyle.solid,
        ),
      ),
      focusedBorder: UnderlineInputBorder(
        borderSide: BorderSide(
          color: isDarkMode ? Colours.darkAppDivider : Colours.appDivider,
          width: 1,
          style: BorderStyle.solid,
        ),
      ),
    ),
    dialogTheme: DialogTheme(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(10),
      ),
      backgroundColor:
      isDarkMode ? Colours.darkDialogBackground : Colors.white,
      titleTextStyle: TextStyle(
        color: isDarkMode ? Colours.darkAppText : Colours.appText,
        fontSize: 20,
      ),
      contentTextStyle: TextStyle(
        color: Colors.yellow,
      ),
    ),
    bottomSheetTheme: BottomSheetThemeData(
      backgroundColor:
      isDarkMode ? Colours.darkAppBackground : Colours.appBackground,
    ),
    dividerColor: isDarkMode ? Colours.darkAppDivider : Colours.appDivider,
    cursorColor: Colours.appThemeColor,
    bottomAppBarTheme: BottomAppBarTheme(
      color: isDarkMode ? Colours.darkAppForeground : Colours.appForeground,
    ),
    toggleButtonsTheme: ToggleButtonsThemeData(color: Colors.yellow),
  );
}