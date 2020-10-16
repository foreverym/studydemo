package club.banyuan.entity;

import club.banyuan.constant.Constant;
import club.banyuan.util.ServerSession;
import club.banyuan.util.SessionUtil;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来封装解析浏览器发送的头信息的数据
 */
public class Request {

  private String url;
  private String host;

  private int contentLength;
  /**
   * 浏览器提交的json数据
   */
  private String data;

  /**
   * 从浏览器发送的请求头中获取cookie数据
   */
  private Map<String, String> cookies = new HashMap<>();

  /**
   * 每个请求都应该有一个session对象
   * session对象可以根据cookie从服务器内存中查询得到
   * 如果查询没有得到，则创建一个新的session，将sessionid写入到cookie中
   */
  private ServerSession session;

  public ServerSession getSession() {
    return session;
  }

  public void setSession(ServerSession session) {
    this.session = session;
  }

  public Map<String, String> getCookies() {
    return cookies;
  }

  public void setCookies(Map<String, String> cookies) {
    this.cookies = cookies;
  }

  /**
   * 将浏览器发送的inputStream中的数据转换为一个Request对象
   * <p>
   * 主要获取url和data
   * url用来判断那个方法处理这个请求或者下载哪个文件
   * data 对应的业务需要使用的 用户数据
   *
   * @param inputStream
   */
  public static Request parse(InputStream inputStream) {
// socket 获取到的是inputStream，需要将inputStream转换为reader 以便更好的处理数据
    String url = null;
    String host = null;
    try {
      // 将input转换为 reader，为了调用readLine API
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(inputStream));

      // 将浏览器的请求头包含的信息转换为一个Request对象
      Request request = new Request();

      // GET /url HTTP/1.1
      // 第一行请求头数据，包含了请求方式和请求路径，解析请求路径，根据这个路径才能判断返回的资源
      String line = reader.readLine();
      url = line.split(" ")[1];
      host = null;
      int contentLength = 0;
      // 后续的请求头中，第二行包含了Host信息
      // 读取空行后，循环退出
      while (line != null && line.length() != 0) {
        if (line.startsWith("Host: ")) {
          host = line.replace("Host: ", "");

        }
        if (line.startsWith("Content-Length: ")) {
          contentLength = Integer.parseInt(line.replace("Content-Length: ", ""));
        }

        if (line.startsWith("Cookie: ")) {
          request.setCookies(parseCookie(line));
        }

        System.out.println(line);
        line = reader.readLine();
      }

      // header头部结束后有数据，读取这些数据
      if (contentLength > 0) {
        char[] payload = new char[contentLength];
        int read = reader.read(payload);
        String data = new String(payload, 0, read);
        System.out.println("data:" + data);
        request.setData(data);
      }

      System.out.println("url:" + url);
      System.out.println("host:" + host);
      System.out.println("Content-length:" + contentLength);
      System.out.println("读取头部文件结束");

      request.setContentLength(contentLength);
      request.setUrl(url);
      request.setHost(host);

      // 处理request的session
      handleSession(request);

      return request;
    } catch (IOException e) {

      e.printStackTrace();
      return null;
    }


  }

  private static void handleSession(Request request) {

    //cookie的类型是键值对类型
    String sessionId = request.getCookies().get(Constant.COOKIE_SESSION_ID);
    if (sessionId == null) {
      // 如果cookie中不包含session id，说明浏览器是第一次请求服务器
      // 需要在最后返回响应头的时候，设置cookie，写入sessionid 的值
      ServerSession session = new ServerSession();
      request.setSession(session);
      // 新建的session需要存入内存中
      SessionUtil.put(session.getSessionId(), session);
    } else {
      ServerSession serverSession = SessionUtil.getSessionById(sessionId);
      if (serverSession == null) {
        // 浏览器带了一个session id 但是服务器内存中，找不到这个id对应的session
        // 创建一个新的session，在返回响应头的时候，重新设置cookie，写入新的session id的值
        ServerSession session = new ServerSession();
        request.setSession(session);
        SessionUtil.put(session.getSessionId(), session);
      } else {
        // 浏览器带过来的session id 有与之对应的session对象，将这个对象写入到request中
        request.setSession(serverSession);
      }
    }

  }

  private static Map<String, String> parseCookie(String line) {
    Map<String, String> rlt = new HashMap<>();
    String cookie = line.replace("Cookie: ", "");
    // cookie中包含的数据，都是 key=value; key=value; 先用 ； 进行拆分，然后逐个使用 =  进行拆分，拆分前先去首位的空格
    String[] split = cookie.split(";");
    for (String keyValue : split) {
      // 因为有可能只有key value是空字符串
      String[] keyValuePair = keyValue.trim().split("=");
      if (keyValuePair.length == 1) {
        rlt.put(keyValuePair[0], "");
      } else if (keyValuePair.length == 2) {
        rlt.put(keyValuePair[0], keyValuePair[1]);
      }
      // 其他的情况不进行处理
    }
    return rlt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getContentLength() {
    return contentLength;
  }

  public void setContentLength(int contentLength) {
    this.contentLength = contentLength;
  }

  public String getData() {
    return data;
  }

  public <T> T parseJsonObject(Class<T> cls) {
    return JSONObject.parseObject(data, cls);
  }

  public void setData(String data) {
    this.data = data;
  }
}
