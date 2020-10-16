package club.banyuan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class Demo1 {

    public static void main(String[] args) {
        //System.out.println("dd".matches("[]"));
        //Pattern pattern = new Pattern("");
        try {
            FileInputStream fileInputStream = new FileInputStream("");

            //fileInputStream.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
