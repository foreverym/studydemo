package club.banyuan.homework;

import javax.crypto.spec.PSource;
import java.io.*;

public class Demo2 {

    public static void main(String[] args) {

    }

    public void getLsOrder(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
    }

    public void getCpOder(String sourcePath, String targetPath) {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        byte[] bytes = new byte[1024];
        if (!sourceFile.exists()) {
            System.out.println("文件不存在");
        }
        if (!targetFile.exists()) {
            System.out.println("文件不存在");
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            inputStream.read(bytes);
            OutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(bytes);
            //byte[]
        } catch (IOException e) {
            e.printStackTrace();
        }
        //InputStream inputStream =
    }


}
