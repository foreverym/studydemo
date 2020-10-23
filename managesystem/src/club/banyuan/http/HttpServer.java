package club.banyuan.http;

import club.banyuan.constant.Constant;
import club.banyuan.entity.Request;
import club.banyuan.exception.BadReqException;
import club.banyuan.util.PropUtil;
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


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {

            System.out.println("创建服务器");
            DispatchRequest dispatchRequest = null;

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
                    if (url.startsWith("/admin/")) {
                        // 处理数据
                        validateUserAuth(request);
                        dispatchRequest = new DispatchRequest();
                        Object rlt = dispatchRequest.dispatchAdminRequest(request);
                        sendJsonResponse(RespStatus.OK, outputStream, rlt, request);
                    }
                    if (url.startsWith("/dept/")) {
                        validateUserAuth(request);
                        dispatchRequest = new DispatchRequest();
                        Object rlt = dispatchRequest.dispatchDeptRequest(request);
                        sendJsonResponse(RespStatus.OK, outputStream, rlt, request);
                    }
                    if (url.startsWith("/position/")) {
                        dispatchRequest = new DispatchRequest();
                        Object rlt = dispatchRequest.dispatchPositionRequest(request);
                        sendJsonResponse(RespStatus.OK, outputStream, rlt, request);
                    }
                    if (url.startsWith("/empl/")) {
                        dispatchRequest = new DispatchRequest();
                        Object rlt = dispatchRequest.dispatchEmployeeRequest(request);
                        sendJsonResponse(RespStatus.OK, outputStream, rlt, request);
                    }

                    else {
                        validatePageAuth(request);
                        sendStaticFile(outputStream, url, request);
                    }
                } catch (BadReqException e) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("code",1);
                    response.put("message", e.getMessage());
                    e.printStackTrace();
                    sendJsonResponse(RespStatus.BAD_REQUEST, outputStream, response, request);
                } catch (RedirectException e) {
                    sendRedirectResponse(RespStatus.REDIRECT, outputStream, e.getMessage(), request);
                } catch (Exception e) {
                    // 服务器在处理请求的时候出现了异常
                    e.printStackTrace();
                    //sendJsonResponse(RespStatus.INTERNAL_SERVER_ERROR, outputStream, e.getMessage(), request);
                    // TODO 返回一个服务器异常状态码
                } finally {
                    if (socket != null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        socket.close();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void sendJsonResponse(RespStatus status, OutputStream outputStream, Object response, Request request)
            throws IOException {
        byte[] bytes = JSONObject.toJSONString(response).getBytes();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(status.getMsg());
        dataOutputStream.writeBytes("Content-Length: " + bytes.length + "\n");
        dataOutputStream.writeBytes("Content-Type: application/json; charset=utf-8\n");
        writeCookie(dataOutputStream, request);
        dataOutputStream.writeBytes("\n");
        dataOutputStream.write(bytes);
    }

    private static void sendRedirectResponse(RespStatus status, OutputStream outputStream,
                                             String message, Request request) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(status.getMsg());
        dataOutputStream.writeBytes("Location: http://" + request.getHost() + message + "\n");
        dataOutputStream.writeBytes("\n");
        dataOutputStream.flush();
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
            Object o = request.getSession().get(Constant.Admin_INFO);
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
            Object o = request.getSession().get(Constant.Admin_INFO);
            if (o == null) {
                throw new BadReqException("用户未登录");
            }
        }
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
            //writeCookie(dataOutputStream, request);
            // html 资源 给定 Content-type  text/html
            // css 资源 给定 Content-type text/css
            if (url.endsWith(".html")) {
                dataOutputStream.writeBytes("Content-Type: text/html; charset=utf-8;\n");
            } else if (url.endsWith(".css")) {
                dataOutputStream.writeBytes("Content-Type: text/css; charset=utf-8;\n");
            }
            dataOutputStream.writeBytes("\n");
            //byte[1024] bytes
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