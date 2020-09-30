package club.banyaun.test;

import javax.swing.plaf.TreeUI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CurrentTime {

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:SS");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(format.format(new Date()));
                //阻塞型操作
                String str = scanner.nextLine();
                if ("quit".equalsIgnoreCase(str)) {
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
