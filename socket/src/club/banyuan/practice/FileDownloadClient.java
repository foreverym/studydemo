package club.banyuan.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileDownloadClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5000);) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.write(1);
            dataInputStream.readByte();
            int jsonBytesLen = dataInputStream.readInt();
            byte[] jsonBytes = new byte[jsonBytesLen];
            dataInputStream.readFully(jsonBytes);


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
