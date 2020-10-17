package club.banyuan.server;

import club.banyuan.constant.Constant;
import club.banyuan.entity.Request;
import club.banyuan.entity.ServerSession;
import club.banyuan.util.SessionUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Map;

public class HttpServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Request request = parseRequest(inputStream);
            if (request.getUrl().startsWith("/server")) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Request parseRequest(InputStream inputStream) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Request request = new Request();
        try {
            String line = bufferedReader.readLine();
            String url = line.replace("Http", "").split(";")[1];
            request.setUrl(url);
            while (line != null && line.length() > 0) {
                if (line.startsWith("Host: ")) {
                    String host = line.replace("Host: ", "");
                    request.setHost(host);
                }
                if (line.startsWith("Cookie: ")) {
                    String cookieStr = line.replace("Cookie: ", "");
                    Map<String, String> cookie = request.parseCookie(cookieStr);
                    request.setCookies(cookie);
                }
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void handleSession(Request request) {
        Map<String, String> cookies = request.getCookies();
        String session_id = cookies.get(Constant.SESSION_ID);
        ServerSession serverSession = new ServerSession();
        SessionUtil sessionUtil = new SessionUtil();
        if (session_id == null) {
            sessionUtil.put(serverSession);
        }
        else {
            if (sessionUtil.get(serverSession.getSessionId()) == null) {

            }
        }
        if (sessionUtil.get(session_id) == null) {

        }
        sessionUtil.put(serverSession);
    }

    public void writeServiceResponse(OutputStream outputStream) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            dataOutputStream.writeBytes("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
