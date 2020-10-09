package club.banyuan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Demo1 {

    private Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(""));
            int read = 0;
            try {
                read = inputStreamReader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (read != -1) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
