package club.banyuan.file;

import java.io.File;

public class Demo2 {

    public static void main(String[] args) {
        File file = new File("wybtest/note1.md");
        System.out.println(file.getAbsolutePath());
    }

}
