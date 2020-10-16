package test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentTest extends Thread {


    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("POST /server/user/login HTTP 1.1 ");
            dataOutputStream.writeBytes("Host: 127.0.0.1:9000");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
