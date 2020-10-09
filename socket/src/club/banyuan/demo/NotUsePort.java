package club.banyuan.demo;

import java.net.DatagramSocket;
import java.net.SocketException;

public class NotUsePort {

    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        for (int i = 0; i < 65536; i++) {
            try {
                datagramSocket = new DatagramSocket(i);
            } catch (SocketException e) {
                System.out.println("端口" + i + "被占用");
            } finally {
                datagramSocket.close();
            }
        }
    }
}
