package club.banyuan.demo;

import javax.print.DocFlavor;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {

    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(10000);
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(datagramPacket);
            System.out.println("接收到数据");
            byte[] data = datagramPacket.getData();
            int length = datagramPacket.getLength();
            String msg = new String(data, 0, length);
            System.out.println(msg);
            datagramSocket = new DatagramSocket(9999);
            datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName("192.168.8.94"), 9999);
            datagramSocket.send(datagramPacket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
