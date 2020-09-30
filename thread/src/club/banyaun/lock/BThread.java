package club.banyaun.lock;

import java.util.concurrent.CountDownLatch;

public class BThread implements Runnable {

    Util util;

    public BThread(Util util) {
        this.util = util;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Util.lock) {
                if (Util.i == 1) {
                    if (util.countDownLatch.getCount() > 0) {
                        System.out.println(Thread.currentThread().getName() + "B");
                        util.countDownLatch.countDown();
                    } else {
                        util.countDownLatch = new CountDownLatch(1);
                        Util.i = 2;
                    }
                }
            }
        }
    }

}
