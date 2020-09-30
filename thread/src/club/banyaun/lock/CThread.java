package club.banyaun.lock;

import java.util.concurrent.CountDownLatch;

public class CThread implements Runnable {

    Util util;

    public CThread(Util util) {
        this.util = util;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Util.lock) {
                if (Util.i == 2) {
                    if (util.countDownLatch.getCount() > 0) {
                        System.out.println(Thread.currentThread().getName() + "C");
                        util.countDownLatch.countDown();
                    } else {
                        util.countDownLatch = new CountDownLatch(1);
                        Util.i = 0;
                    }
                }
            }
        }
    }

}
