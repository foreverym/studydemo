package club.banyuan.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.SocketHandler;

public class TcpClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.8.94", 5000);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("你好哇".getBytes());
            //用来关闭一个输出流，传递一个结束的标志
            socket.shutdownOutput();


            InputStream inputStream = socket.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            String msg = new String(bytes);
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
