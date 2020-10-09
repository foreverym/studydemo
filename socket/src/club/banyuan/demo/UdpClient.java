package club.banyuan.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * socket类，相当于网络通信，网络通信的两台计算机的抽象
 */
public class UdpClient {

    public static void main(String[] args) {
        //建立udp通信，指定一个端口号或者系统分配一个端口号
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(9999);
            String msg = "你好哇";
            DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName("192.168.8.94"), 9999);
            datagramSocket.send(datagramPacket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}
