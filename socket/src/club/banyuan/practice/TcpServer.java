package club.banyuan.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 先发一个byte或一行，告诉服务器端是加密还是解密，然后再发一个byte或一行，告诉服务器端的总共的文本数量。
 * 然后将文本发送给服务器端。服务器端返回加密或解密后的文本。
 *
 * 服务器先发送文本长度，然后在发送文本内容，将加密或解密后的内容返回给客户端。
 */
public class TcpServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;
        try {
            serverSocket = new ServerSocket(5000);
            Socket socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = bufferedReader.readLine();

            
            if ("decode".equals(s)) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
