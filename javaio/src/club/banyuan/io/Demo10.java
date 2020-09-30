package club.banyuan.io;

import java.io.*;

public class Demo10 {

    public static void main(String[] args) {
        File file = new File("javaio/1.txt");
        File file1 = new File("javaio/2.txt");
        byte[] bytes = new byte[(int)file.length()];
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file1);
            InputStream in = new FileInputStream(file);
            int read = in.read();
//            while (read != -1) {
//                out.write(read);
//            }
            int count = in.read(bytes);

            while (count != -1) {
                out.write(bytes, 0, count);
                count = in.read(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
