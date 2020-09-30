package club.banyuan.io;

import java.io.File;
import java.io.IOException;

public class Demo9 {

    public static void main(String[] args) {
        File file = new File("javaio/123.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
