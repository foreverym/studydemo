package club.banyuan;

import java.util.Random;

public class Test1 {

    public static void main(String[] args) {
//        for (int i = 0; i < 40; i++)  {
//            System.out.println(i);
//        }

        testRandom();
    }

    public static void testRandom() {
        Random random = new Random();
        //System.out.println(random.nextInt(7));

        for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt(20));
        }
    }


}
