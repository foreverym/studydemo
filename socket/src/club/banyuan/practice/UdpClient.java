package club.banyuan.practice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        if ("quit".equals(s)) {

        } else {
            s.trim();
            String[] splitStrings = s.split(" ");
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket(10000);
                DatagramPacket datagramPacket = new DatagramPacket(splitStrings[2].getBytes(),
                        splitStrings[2].getBytes().length, InetAddress.getByName(splitStrings[0]), Integer.valueOf(splitStrings[1]));
                datagramSocket.send(datagramPacket);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                datagramSocket.close();
            }
        }

    }

}
