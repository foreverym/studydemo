package club.banyaun;

import java.util.concurrent.TimeUnit;

public class Demo3 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toSeconds(100));
                System.out.println(Thread.currentThread().getName()+ "---" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


class Test3 {


}