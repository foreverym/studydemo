package club.banyuan.practice;

import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileDownloadServer {

    public static void main(String[] args) {
        File folder = new File("");
        File[] files = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });

        List<String> fileList = new ArrayList<>();

        for (File file : files) {
            fileList.add(file.getName());
        }


        //服务器端等待客户端发送命令
        //1.byte类型 1-获取文件列表，2-下载文件
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte flag = dataInputStream.readByte();

            switch (flag) {
                case 1:
                    //获取文件列表
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeByte(1);
                    byte[] bytes = JSONObject.toJSONString(fileList).getBytes();
                    dataOutputStream.writeInt(bytes.length);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Arrays.stream(files).map(file -> file.getName()).collect(Collectors.joining())

    }


}
