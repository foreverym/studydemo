package club.banyuan.http;

import club.banyuan.util.PropUtil;
import club.banyuan.entity.User;
import club.banyuan.service.UserService;
import com.alibaba.fastjson.JSONObject;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理两种类型的请求数据
 * <p>
 * 1. 请求 html文件、css、js、图片文件的请求
 * <p>
 * 2. 类似于登录的数据处理的请求
 * /server/user/login
 * <p>
 * 根据请求路径的不同，区分返回文件还是处理数据
 */
public class HttpServerStep2 {

  /**
   * 引入UserService对象，处理用户相关的业务逻辑
   */
  private static UserService userService = new UserService();

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(9000)) {

      System.out.println("创建服务器");

      while (true) {
        try (Socket socket = serverSocket.accept();) {
          System.out.println("客户端接入");
          Request request = Request.parse(socket.getInputStream());
          OutputStream outputStream = socket.getOutputStream();
          String url = request.getUrl();
          // 和前台代码匹配的。以/server 路径开头的，表示需要进行数据处理
          if (url.startsWith("/server")) {
            // 处理数据
            dispatchRequest(request, outputStream);
          } else {
            // 没有以server 开头，表示请求的是资源，html、css、js、图片等文件，返回文件
            sendStaticFile(outputStream, url);
          }
        } catch (IOException e) {
          // 服务器在处理请求的时候出现了异常
          e.printStackTrace();
          // TODO 返回一个服务器异常状态码
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 根据Url不同，使用不同的方法进行处理
   *
   * @param request
   * @param outputStream
   */
  private static void dispatchRequest(Request request, OutputStream outputStream)
      throws IOException {
    String url = request.getUrl();
    switch (url) {
      case "/server/user/login": {
        // String data = request.getData();
        // User user = JSONObject.parseObject(data, User.class);
        User user = request.parseJsonObject(User.class);
        User loginUser = userService.login(user);
        if (loginUser == null) {
          // 登录失败
          sendFail(outputStream, "用户名或密码错误");
        } else {
          // 登录成功
          sendSuccess(outputStream);
        }
      }
      break;
      case "/server/user/list": {
        User user = request.parseJsonObject(User.class);
        List<User> userList = userService.getUserList(user);

        Map<String, Object> rlt = new HashMap<>();
        rlt.put("data", userList);

        byte[] bytes = JSONObject.toJSONString(rlt).getBytes();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");

        dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
        dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
        dataOutputStream.writeBytes("\n");
        dataOutputStream.write(bytes);
      }
      break;
      case "/server/user/add": {
        User user = request.parseJsonObject(User.class);
        userService.addUser(user);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        Map<String, String> response = new HashMap<>();
        response.put("data", "操作成功");

        byte[] bytes = JSONObject.toJSONString(response).getBytes();

        dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
        dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
        dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
        dataOutputStream.writeBytes("\n");
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
      }
      break;

      case "/server/user/get": {
        // 根据ID 查询单个用户，前台发送 数据只包含id的信息，但是我们可以使用user对象来接收这个id信息
        // user对象中只有id是有用的
        User user = request.parseJsonObject(User.class);
        User userInfo = userService.getUserById(user);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        Map<String, Object> response = new HashMap<>();
        response.put("data", userInfo);

        byte[] bytes = JSONObject.toJSONString(response).getBytes();

        dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
        dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
        dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
        dataOutputStream.writeBytes("\n");
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
      }
      break;

      case "/server/user/update":{
        User user = request.parseJsonObject(User.class);
        userService.updateUser(user);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        Map<String, String> response = new HashMap<>();
        response.put("data", "操作成功");

        byte[] bytes = JSONObject.toJSONString(response).getBytes();

        dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
        dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
        dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
        dataOutputStream.writeBytes("\n");
        dataOutputStream.write(bytes);
        dataOutputStream.flush();

      }

      default:
        // TODO 异常
        break;
    }
  }

  private static void sendSuccess(OutputStream outputStream) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
    // 响应头
    // 字符串 换行
    // 字符串 换行
    // Content-Length: 1234  换行之后的数据长度
    // 。。。。
    // 换行
    // bytes数组
    dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
    dataOutputStream.writeBytes("\n");
    dataOutputStream.flush();
  }

  private static void sendFail(OutputStream outputStream, String msg) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
    // 响应头
    // 字符串 换行
    // 字符串 换行
    // Content-Length: 1234  换行之后的数据长度
    // 。。。。
    // 换行
    // bytes数组

    Map<String, String> response = new HashMap<>();
    response.put("data", msg);

    byte[] bytes = JSONObject.toJSONString(response).getBytes();

    dataOutputStream.writeBytes("HTTP/1.1 400 Bad request\n");
    dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
    dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
    dataOutputStream.writeBytes("\n");
    dataOutputStream.write(bytes);
    // {data: msg}

    dataOutputStream.flush();
  }


  private static void sendStaticFile(OutputStream outputStream, String url) throws IOException {
    File file = new File(PropUtil.getProp("page.root"), url);
    if (!file.isFile()) {
      file = new File(PropUtil.getProp("page.root"), "/404.html");
    }
    try (FileInputStream fileInputStream = new FileInputStream(file)) {
      DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
      // 响应头
      // 字符串 换行
      // 字符串 换行
      // Content-Length: 1234  换行之后的数据长度
      // 。。。。
      // 换行
      // bytes数组
      dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
      dataOutputStream.writeBytes("Content-Length: " + fileInputStream.available() + "\n");

      // html 资源 给定 Content-type  text/html
      // css 资源 给定 Content-type text/css
      if (url.endsWith(".html")) {
        dataOutputStream.writeBytes("Content-Type: text/html; charset=utf-8;\n");
      } else if (url.endsWith(".css")) {
        dataOutputStream.writeBytes("Content-Type: text/css; charset=utf-8;\n");
      }
      dataOutputStream.writeBytes("\n");
      dataOutputStream.write(fileInputStream.readAllBytes());
      dataOutputStream.flush();
    }
  }
}