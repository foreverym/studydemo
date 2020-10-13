package club.banyuan;

import java.io.*;
import java.net.Socket;

public class HttpClient1 {

    public static void main(String[] args) {
            try {
                Socket socket = new Socket("127.0.0.1", 9000);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes("GET /login.html HTTP/1.1\n");
                dataOutputStream.writeBytes("Host: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "\n");
                dataOutputStream.writeBytes("\n");
                dataOutputStream.flush();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();
                int contentLength = 0;
                while (line != null && line.length() > 0) {
                    if (line.startsWith("Content-Length: ")) {
                        contentLength = Integer.parseInt(line.replaceAll("Content-Length: ", ""));
                    }
                    line = bufferedReader.readLine();
                }

                if (contentLength > 0) {
                    char[] payload = new char[contentLength];
                    int read = bufferedReader.read(payload);
                    String data = new String(payload, 0, read);
                    System.out.println(data);
                    System.out.println(payload.length);
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
