package club.banyuan.tcp;

import java.io.*;
import java.net.Socket;

public class FileTcpClient {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("192.168.8.94", 5000);
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = bufferedInputStream.readAllBytes();
            FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
            fileOutputStream.write(bytes);
            //ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedInputStream)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
