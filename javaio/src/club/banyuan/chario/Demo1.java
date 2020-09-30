package club.banyuan.chario;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

public class Demo1 {

    public static void main(String[] args) {
        //读取utf-8编码的文件
        try {
            FileReader fileReader = new FileReader("javaio/1.txt");
            OutputStream outputStream = new FileOutputStream("javaio/2.txt");

            StringBuilder stringBuilder = new StringBuilder();

            char[] buf = new char[1024];

            int count = fileReader.read(buf);
            while (count != -1) {
                stringBuilder.append(buf, 0, count);
                //System.out.println((char)read);
                count = fileReader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
