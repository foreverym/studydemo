package club.banyuan.io;

import java.io.*;

public class Demo1 {

    public static void main(String[] args) {
        File file = new File("wybtext/note1.md");
        InputStream inputStream = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            inputStream.read(bytes, 5, 9);
            String str = new String(bytes);
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
