class UserInfoManager {
  String? token;

  static UserInfoManager? _instance;

  UserInfoManager._internal();

  factory UserInfoManager.getInstance() => _getInstance();

  static _getInstance() {
    if (_instance == null) {
      _instance = UserInfoManager._internal();
    }
    return _instance;
  }

  void setUserToken(String userToken) {
    token = userToken;
  }

  String? getUserToken() {
    return token;
  }
}
