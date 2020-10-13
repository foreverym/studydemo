package club.banyuan;

import club.banyuan.entity.User;
import club.banyuan.service.UserService1;
import club.banyuan.util.PropUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {


    public static void main(String[] args) {

        UserService1 userService = new UserService1();

        try (ServerSocket serverSocket = new ServerSocket(9000)) {

            System.out.println("创建服务器");

            while (true) {
                try (Socket socket = serverSocket.accept();) {
                    System.out.println("客户端接入");
                    // 读取浏览器发送的信息
                    // Request Head 信息
                    // 字符串 换行
                    // 字符串 换行
                    // ......
                    // 换行

                    // 请求头的头两行内容，第一行包含 请求方式  请求路径  协议版本
                    // 第二行包含 服务器路径
                    // GET /12345/abcd/123 HTTP/1.1
                    // Host: 127.0.0.1:9000

                    // socket 获取到的是inputStream，需要将inputStream转换为reader 以便更好的处理数据
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    String line = reader.readLine();
                    String url = line.split(" ")[1];
                    String host = null;
                    int contentLength = 0;
                    while (line != null && line.length() != 0) {
                        if (line.startsWith("Host: ")) {
                            host = line.replace("Host: ", "");
                        }
                        if (line.startsWith("Content-Length: ")) {
                            contentLength = Integer.valueOf(line.replace("Content-Length: ", ""));
                        }
                        System.out.println(line);
                        line = reader.readLine();
                    }

                    System.out.println("url:" + url);
                    System.out.println("host:" + host);
                    System.out.println("读取头部文件结束");

                    // 根据浏览器发送的请求路径选择指定的资源下发给浏览器
                    // 服务器端需要指定一个资源的根路径，将浏览器请求的匹配的资源路径的文件发送给浏览器

                    // 发送文件数据之前，需要先发送 响应头
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    if (url.startsWith("/server")) {
                        switch (url) {
                            case "/server/user/login":
                                char[] chars = new char[contentLength];
                                int read = reader.read(chars);
                                String data = new String(chars, 0, read);
                                User user = JSONObject.parseObject(data, User.class);
                                if (user == null) {
                                    //抛出异常
                                } else {
                                    //登录成功
                                }
                                break;
                            case "/server/user/list":
//                                List<User> userList = userService.getUserList();
//                                Map<String, List<User1>> userMap = new HashMap<>();
//                                userMap.put("data", userList);
                                //String userMapJsonString = JSONObject.toJSONString(userMap);
                                dataOutputStream.writeBytes("HTTP1.1 200 OK\n");
//                                dataOutputStream.writeBytes("Content-Length: " + userMapJsonString.getBytes().length + "\n");
//                                dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
//                                dataOutputStream.writeBytes("\n");
//                                dataOutputStream.writeBytes(userMapJsonString);
                                dataOutputStream.flush();
                                break;
                            case "/server/user/add":
                                char[] chars1 = new char[contentLength];
                                int read1 = reader.read(chars1);
                                String data1 = new String(chars1, 0, read1);
                                //User1 user1 = JSONObject.parseObject(data1, User1.class);
//                                System.out.println(user1);
//                                userService.addUser(user1);
                                break;
                            case "/server/user/update":

                        }
                    } else {
                        File file = new File(PropUtil.getProp("page.root"), url);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        if (!file.isFile()) {
                            file = new File(PropUtil.getProp("page.root"), "/404.html");
                        }
                        // 响应头
                        // 字符串 换行
                        // 字符串 换行
                        // 。。。。
                        // 换行
                        // bytes数组
                        dataOutputStream.writeBytes("HTTP1.1 200 OK\n");
                        dataOutputStream.writeBytes("Content-Length: " + fileInputStream.available() + "\n");
                        dataOutputStream.writeBytes("\n");
                        //fileInputStream.read()
                        dataOutputStream.write(fileInputStream.readAllBytes());
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}