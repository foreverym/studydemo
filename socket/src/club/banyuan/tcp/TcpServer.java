package club.banyuan.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            //阻塞等待连接
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("1.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            int count = 0;
            String line = bufferedReader.readLine();

            while (count < 10) {
                bufferedWriter.write(line);
                line = bufferedReader.readLine();
                count++;
            }
//            byte[] bytes = new byte[1024];
//            int read = inputStream.read(bytes);
//            while (read != -1) {
//
//            }
            byte[] bytes = inputStream.readAllBytes();
            String msg = new String(bytes);
            System.out.println(msg);
            System.out.println("有程序接入");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
