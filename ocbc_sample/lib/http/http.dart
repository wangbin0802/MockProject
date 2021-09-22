import 'dart:convert';

import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:ocbcsample/http/api.dart';
import 'package:ocbcsample/http/interceptor.dart';
import 'package:ocbcsample/utils/file_utils.dart';


class HttpClient {
  static const int ERROR_DIO = 101;
  static const int ERROR_PARSE = 102;

  static const String GET = "GET";
  static const String POST = "POST";
  late Dio dio;
  late PersistCookieJar persistCookieJar;

  /// 私有构造函数
  HttpClient._internal() {
    dio = Dio();
    dio.options.baseUrl = Api.BASE_URL;
    dio.options.connectTimeout = 10 * 1000;
    dio.options.sendTimeout = 10 * 1000;
    dio.options.receiveTimeout = 10 * 1000;
    dio.interceptors.add(HeaderInterceptor());
    dio.interceptors
        .add(LogInterceptor(requestBody: true, responseBody: true));

    /// cookie
    getCookiePath().then((path) {
      persistCookieJar = PersistCookieJar(dir: path);
      CookieManager cookieManager = CookieManager(persistCookieJar);
      dio.interceptors.add(cookieManager);
    });
  }

  /// 保存单例对象
  static final HttpClient _client = HttpClient._internal();

  factory HttpClient() => _client;

  static HttpClient getInstance() {
    return _client;
  }

  ///  GET 请求
  get(String path, {required Map<String, dynamic> data}) async {
    return _request(path, GET, data: data);
  }

  /// POST 请求
  post(String path, {required Map<String, String> data}) async {
    return _request(path, POST, data: data);
  }

  /// 私有方法，只可本类访问
  _request(String path, String method, {required Map<String, dynamic> data}) async {
    data = data;
    var tempData;
    method = method;
    if (method == GET) {
      data.forEach((key, value) {
        if (path.contains(key)) {
          path = path.replaceAll(":$key", value.toString());
        }
      });
    } else if (method == POST) {
      tempData = FormData.fromMap(data);
    }
    try {
      Response response = await dio.request(path,
          data: tempData, options: Options(method: method));
      if (response.statusCode != 200) {
        _handleError(response.statusCode, response.statusMessage);
        return;
      }
      var jsonString = json.encode(response.data);
      // map中的泛型为 dynamic
      Map<String, dynamic> dataMap = json.decode(jsonString);
      // ignore: unnecessary_null_comparison
      if (dataMap != null) {
        int errorCode = dataMap['errorCode'];
        String errorMsg = dataMap['errorMsg'];
        bool error = dataMap['error'] ?? true;
        var results = dataMap['results'];
        var data = dataMap['data'];
        // 请求失败
        if (errorCode != 0 && error) {
          _handleError(errorCode, errorMsg);
          return;
        }
        if (data != null) {
          return data;
        } else if (results != null) {
          return results;
        }
      } else {
        _handleError(ERROR_PARSE, "数据解析失败");
      }
    } on DioError catch (e) {
      // 请求错误
      _handleError(ERROR_DIO, "网络连接异常");
    }
  }

  void _handleError(int errorCode, String errorMsg) {
    print("_handleError = $errorMsg");
    Fluttertoast.showToast(
        msg: errorMsg,
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM);
    // 未登录
    if (errorCode == -1001) {}
  }
}
