package club.banyuan;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginHttpServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            Socket socket = serverSocket.accept();
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();
                String[] split = line.split("");
                String url = split[1];
                String host = null;
                int contentLength = 0;
                while (line != null && line.length() > 0) {
                    System.out.println(line);
                    if (line.startsWith("Host: ")) {
                        host = line.replaceFirst("Host: ", "");
                    }
                    if (line.startsWith("Content-Length: ")) {
                        contentLength = Integer.valueOf(line.replaceFirst("Content-Length: ", ""));
                    }
                    line = reader.readLine();
                }

                System.out.println(url);
                System.out.println(host);
                System.out.println(contentLength);
                System.out.println("读取完毕");

                //读取数据
                if (contentLength > 0) {
                    char[] payload = new char[contentLength];
                    int read = reader.read(payload);
                    String data = new String(payload, 0, read);
                    //User user = JSONObject.parseObject(data, User.class);

                    System.out.println("data:" + data);
                }

                String response = "消息已收到";
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes("HTTP/1.1 200 OK\n");
                dataOutputStream.writeBytes("Content-Length: " + response.getBytes().length + "\n");
                // 解决乱码 TODO
                dataOutputStream.writeBytes("Content-Type: plain/text; charset=utf-8;\n");
                dataOutputStream.writeBytes("\n");
                dataOutputStream.write(response.getBytes());
                dataOutputStream.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
