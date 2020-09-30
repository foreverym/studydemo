package club.banyuan.io;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Demo11 {

    public static void main(String[] args) {
        String s = "你好呀";
        try {
            byte[] bytes = s.getBytes("GBK");
            System.out.println(bytes.length);
            System.out.println(Arrays.toString(bytes));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
