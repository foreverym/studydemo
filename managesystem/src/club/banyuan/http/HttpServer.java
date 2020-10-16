package club.banyuan.http;

import club.banyuan.constant.Constant;
import club.banyuan.entity.Bill;
import club.banyuan.entity.Request;
import club.banyuan.service.BillService;
import club.banyuan.util.PropUtil;
import club.banyuan.entity.Provider;
import club.banyuan.entity.User;
import club.banyuan.service.ProviderService;
import club.banyuan.service.UserService;
import club.banyuan.util.ServerSession;
import com.alibaba.fastjson.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
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
public class HttpServer {

    /**
     * 引入UserService对象，处理用户相关的业务逻辑
     */
    private static UserService userService = new UserService();
    private static ProviderService providerService = new ProviderService();
    private static BillService billService = new BillService();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {

            System.out.println("创建服务器");

            while (true) {
                OutputStream outputStream = null;
                Socket socket = null;
                Request request = null;
                try {
                    socket = serverSocket.accept();
                    System.out.println("客户端接入");
                    request = Request.parse(socket.getInputStream());
                    outputStream = socket.getOutputStream();
                    String url = request.getUrl();


                    // 和前台代码匹配的。以/server 路径开头的，表示需要进行数据处理
                    if (url.startsWith("/server")) {
                        // 处理数据
                        validateUserAuth(request);
                        Object rlt = dispatchRequest(request);
                        sendJsonResponse(RespStatus.OK, outputStream, rlt, request);
                    } else {
                        // 没有以server 开头，表示请求的是资源，html、css、js、图片等文件，返回文件
                        validatePageAuth(request);
                        sendStaticFile(outputStream, url, request);
                    }
                } catch (BadReqException e) {
                    e.printStackTrace();
                    sendJsonResponse(RespStatus.BAD_REQUEST, outputStream, e.getMessage(), request);
                } catch (Exception e) {
                    // 服务器在处理请求的时候出现了异常
                    e.printStackTrace();
                    sendJsonResponse(RespStatus.INTERNAL_SERVER_ERROR, outputStream, e.getMessage(), request);
                    // TODO 返回一个服务器异常状态码
                } finally {
                    if (socket != null) {
                        socket.close();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void sendJsonResponse(RespStatus status, OutputStream outputStream, Object msg, Request request)
            throws IOException {
        Map<String, Object> rlt = new HashMap<>();
        rlt.put("data", msg);
        byte[] bytes = JSONObject.toJSONString(rlt).getBytes();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(status.getMsg());
        dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
        dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
        writeCookie(dataOutputStream, request);
        dataOutputStream.writeBytes("\n");
        dataOutputStream.write(bytes);
    }

    /**
     * 校验请求静态资源时，用户是否登录
     *
     * @param request
     */
    private static void validatePageAuth(Request request) {
        String url = request.getUrl();
        // 登录页面不需要做校验， html结尾的需要做校验
        if (url.endsWith(".html") && !url.equals("/login.html")) {
            Object o = request.getSession().get(Constant.USER_INFO);
            if (o == null) {
                throw new RedirectException("/login.html");
            }
        }
    }

    /**
     * 有些接口，必须保证用户已经登录才能正常操作
     *
     * @param request
     */
    private static void validateUserAuth(Request request) {
        String url = request.getUrl();
        // 除了login接口，其他的接口，都需要用户已经登录
        // 从request中获取session，查看session是否有用户登录数据
        if (!url.endsWith("login")) {
            Object o = request.getSession().get(Constant.USER_INFO);
            if (o == null) {
                throw new BadReqException("用户未登录");
            }
        }
    }


    /**
     * 根据Url不同，使用不同的方法进行处理
     *
     * @param request
     */
    private static Object dispatchRequest(Request request) {
        String url = request.getUrl();
        switch (url) {
            case "/server/user/login": {
                User user = request.parseJsonObject(User.class);
                User loginUser = userService.login(user);
                if (loginUser == null) {
                    // 登录失败
                    throw new BadReqException("用户名或密码错误");
                } else {
                    // 登录成功
                    // sendSuccess(outputStream);
                    //写入cookie
                    request.getSession().put(Constant.USER_INFO, loginUser);
                }
            }
            break;
            case "/server/user/list": {
                User user = request.parseJsonObject(User.class);
                return userService.getUserList(user);
            }

            case "/server/user/add": {
                User user = request.parseJsonObject(User.class);
                userService.addUser(user);
            }
            break;

            case "/server/user/get": {
                // 根据ID 查询单个用户，前台发送 数据只包含id的信息，但是我们可以使用user对象来接收这个id信息
                // user对象中只有id是有用的
                User user = request.parseJsonObject(User.class);
                return userService.getUserById(user);
            }
            case "/server/user/update": {
                User user = request.parseJsonObject(User.class);
                userService.updateUser(user);
            }
            break;

            case "/server/user/delete": {
                User user = request.parseJsonObject(User.class);
                userService.deleteUser(user);
            }
            break;

            case "/server/provider/add": {
                Provider provider = request.parseJsonObject(Provider.class);
                providerService.addProvider(provider);
            }
            break;

            case "/server/provider/update": {
                Provider provider = request.parseJsonObject(Provider.class);
                providerService.updateProvider(provider);
            }
            break;

            case "/server/provider/list": {
                Provider provider = request.parseJsonObject(Provider.class);
                return providerService.getListProvider(provider);
            }

            case "/server/provider/get": {
                Provider provider = request.parseJsonObject(Provider.class);
                return providerService.getProviderInfo(provider);
            }

            case "/server/provider/delete": {
                Provider provider = request.parseJsonObject(Provider.class);
                providerService.deleteProvider(provider);
            }
            break;

            case "/server/bill/add": {
                Bill bill = request.parseJsonObject(Bill.class);
                billService.addBill(bill);
            }
            break;

            case "/server/bill/update": {
                Bill bill = request.parseJsonObject(Bill.class);
                billService.updateBill(bill);
            }
            break;

            case "/server/bill/get": {
                Bill bill = request.parseJsonObject(Bill.class);
                return billService.getBillInfo(bill);
            }

            case "/server/bill/list": {
                Bill bill = request.parseJsonObject(Bill.class);
                return billService.getListBill(bill);
            }
            default:
                // TODO 异常
                break;

        }

        // 如果请求处理数据没有特殊的返回对象，统一返回一个操作成功的字符串
        return "操作成功";
    }

    private static void sendStaticFile(OutputStream outputStream, String url, Request request) throws IOException {
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
            writeCookie(dataOutputStream, request);
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

    /**
     * 将sessionid写入
     * @param outputStream
     * @param request
     * @throws IOException
     */
    private static void writeCookie(DataOutputStream outputStream, Request request)
            throws IOException {
        ServerSession session = request.getSession();
        String sessionId = session.getSessionId();
        if (session.isValid()) {
            //获得session只需要获取一个COOKIE_SESSION_ID
            outputStream
                    .writeBytes("Set-Cookie: " + Constant.COOKIE_SESSION_ID + "=" + sessionId + "; path=/\n");
        } else {
            //如果session失效，将cookie中的sessionId置为失效，给Max-Age=0
            outputStream
                    .writeBytes("Set-Cookie: " + Constant.COOKIE_SESSION_ID + "=" + sessionId
                            + "; path=/; Max-Age=0\n");
        }

    }

}