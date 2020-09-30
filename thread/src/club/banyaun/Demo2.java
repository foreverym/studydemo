package club.banyaun;

import java.util.concurrent.TimeUnit;

public class Demo2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

}


class Test2 {

    public static void main(String[] args) {
        Demo2  demo2 = new Demo2();

        Thread thread1 = new Thread(demo2);
        thread1.start();
        System.out.println(">>>>>>>>>" + thread1.getState());


        Thread thread2 = new Thread(demo2);
        thread2.start();
        System.out.println("<<<<<<<<<" + thread2.getState());
    }

}