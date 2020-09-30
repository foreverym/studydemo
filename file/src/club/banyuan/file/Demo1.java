package club.banyuan.file;

import java.io.File;
import java.io.IOException;

public class Demo1 {

    public static void main(String[] args) throws IOException {
        File file = new File("note.md");  //文件默认去项目下查找
        System.out.println(file.exists());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());

        //file.renameTo(new File("note1.md"));
        //file.createNewFile();

        file.setReadOnly();

        File file1 = new File("wybtwst/src/banyuan");
        System.out.println(file1.mkdir());
    }
}
