package club.banyuan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClient {

  public static void main(String[] args) {
    try (Socket socket = new Socket("127.0.0.1", 9000);) {

      // 写请求头
      DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
      outputStream.writeBytes("GET /login.html HTTP/1.1\n");
      String hostStr =
          "Host: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "\n";
      outputStream.writeBytes(hostStr);
      // 发送一个空行表示请求头发送完成
      // outputStream.writeBytes("\n");
      outputStream.writeBytes("\n");
      outputStream.flush();

      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String line = reader.readLine();
      int contentLength = 0;
      while (line != null && line.length() != 0) {
        if (line.startsWith("Content-Length: ")) {
          contentLength = Integer.parseInt(line.replace("Content-Length: ", ""));
        }
        line = reader.readLine();
      }

      if (contentLength > 0) {
        // char[] payload = new char[contentLength];
        // int read = reader.read(payload);
        // String data = new String(payload, 0, read);
        // System.out.println("read char length:" + read);
        // System.out.println("data bytes:" + data.getBytes().length);
        // System.out.println("contentLength:" + contentLength);
        // System.out.println(data);

        // StringBuilder builder = new StringBuilder();
        // // ready 返回true 表示调用read方法不会被阻塞，直接可以读取到一个char
        // while (reader.ready()) {
        //   builder.append((char) reader.read());
        // }
        //
        // System.out.println(builder.toString());
        // System.out.println(builder.toString().getBytes().length == contentLength);
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
