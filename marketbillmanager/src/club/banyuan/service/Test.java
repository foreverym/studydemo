package club.banyuan.service;

import java.awt.font.FontRenderContext;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            //if ("")
            iterator.remove();
        }
        
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = bufferedReader.readLine();
            while (line != null && line.length() > 0) {
                if (line.startsWith("Cookie: ")) {
                    String cookie = line.replaceFirst("Cookie: ", "");
                    String[] split = cookie.split(";");
                    for (String keyValuePair : split) {
                        String[] keyValue = keyValuePair.trim().split("=");

                    }


                }
            }

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("Set:Cookie: " + "abcde=1123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
