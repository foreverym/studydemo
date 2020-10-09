package club.banyuan.practice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * ### 编写一个Udp服务器，服务器接收Udp客户端发送的消息，另外服务器也会接收键盘输入，当键盘输入quit，则服务器程序退出
 *
 * ### 编写一个Udp客户端，从键盘接收目标ip地址，端口号和发送的消息，将消息发送到指定位置
 *
 *
 */
public class UdpServer {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9999);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println("接收到数据");
            byte[] data = packet.getData();
            int length = packet.getLength();
            String s = new String(data, 0, length);
            System.out.println(s);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
