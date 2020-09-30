package club.banyuan.buffer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class Demo1 {
    
    static Integer integer = 0;

    public static void main(String[] args) throws Exception {
        /**
         * 装饰者模式，装饰者具有和被装饰者同样的方法，但是具有更强的实现
         */
        InputStream inputStream = new FileInputStream("javaio/1.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);


    }
}
