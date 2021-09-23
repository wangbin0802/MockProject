import 'package:flutter/material.dart';

class DarkMode with ChangeNotifier {
  /// 夜间模式
  bool _isDark = false;

  void setDark(isDark) {
    _isDark = isDark;
    notifyListeners();
  }

  get isDark => _isDark;
}
