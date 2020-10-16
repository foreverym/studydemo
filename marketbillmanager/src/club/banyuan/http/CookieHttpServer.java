package club.banyuan.http;

import club.banyuan.constant.Constant;
import club.banyuan.entity.User;
import club.banyuan.util.ServerSession;
import club.banyuan.util.SessionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CookieHttpServer {

    public static void main(String[] args) {
        while (true) {
            ServerSocket serverSocket = null;
            BufferedReader bufferedReader = null;
            try {
                serverSocket = new ServerSocket(5000);
                Socket socket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();
                String url = line.split(" ")[1];
                String hsot = null;
                String cookieStr = null;
                while (line != null && line.length() > 0) {
                    if (line.startsWith("Host: ")) {
                        hsot = line.replaceAll("Host: ", "");
                    }
                    if (line.startsWith("Cookie: ")) {
                        cookieStr = line.replaceFirst("Cookie: ", "");
                    }
                }

                //得到cookie
                Map<String, String>  cookies = new HashMap<>();
                String[] keyValue = cookieStr.split(";");
                for (String keyValuePair : keyValue) {
                    String[] split = keyValuePair.split("=");
                    if (split.length == 1) {
                        cookies.put(split[0], "");
                    }
                    if (split.length == 2) {
                        cookies.put(split[0], split[1]);
                    }
                }

                //ServerSession serverSession = new ServerSession();
                String sessionId = cookies.get(Constant.COOKIE_SESSION_ID);
                if (sessionId == null) {
                    ServerSession serverSession = new ServerSession();
                    sessionId = serverSession.getSessionId();
                    SessionUtil.put(sessionId, serverSession);
                } else {
                    if (SessionUtil.getSessionById(sessionId) == null) {
                        ServerSession serverSession = new ServerSession();
                        sessionId = serverSession.getSessionId();
                        SessionUtil.put(sessionId, serverSession);
                    } else {

                    }
                }

                //进行登录时，会保存用户信息
                User user = new User();
                user.setName("admin");
                user.setPwd("123456");
                //serverSession.put(Constant.USER_INFO, user);

                //SessionUtil.put(Constant.COOKIE_SESSION_ID, serverSession);

                //ThreadLocalRandom threadLocalRandom = new ThreadLocalRandom();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
