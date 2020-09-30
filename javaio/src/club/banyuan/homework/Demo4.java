package club.banyuan.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo4 {

    public static void main(String[] args) {
        File file = new File("javaio/1.txt");
        byte[] bytes = new byte[10];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //文件流是存储在内存中的，会自动往后读取
            fileInputStream.read(bytes);
            String str = new String(bytes);
            System.out.println(str);
            fileInputStream.read(bytes);
            String str2 = new String(bytes);
            System.out.println(str2);
            //获取文件中还未读取的字节数
            System.out.println(fileInputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
