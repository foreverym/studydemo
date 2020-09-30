package club.banyaun.practice;

import java.util.ArrayList;
import java.util.List;

public class Person {

    public static void main(String[] args) {

        BankCount bankCount = new BankCount(true, 1000);
        Table table = new Table(bankCount);
        Atm atm = new Atm(bankCount);
        Thread thread1 = new Thread(table);
        Thread thread2 = new Thread(atm);
        thread1.setName("柜台");
        thread2.setName("ATM");
        thread1.start();
        thread2.start();

        List<String> list = new ArrayList<>(1000);

        for (int i = 0; i < 10; i++) {
            List<String> list1 = list.subList(i, ((i+1) * 1000) - 1);

        }

    }

}
