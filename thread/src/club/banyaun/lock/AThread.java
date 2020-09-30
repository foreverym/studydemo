package club.banyaun.lock;

import java.util.concurrent.CountDownLatch;

public class AThread implements Runnable {

    public static final String HHHH_JJJJ = "";
    Util util;

    public AThread(Util util) {
        this.util = util;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Util.lock) {
                if (Util.i == 0) {
                    if (util.countDownLatch.getCount() > 0) {
                        System.out.println(Thread.currentThread().getName() + "A");
                        util.countDownLatch.countDown();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        util.countDownLatch = new CountDownLatch(1);
                        Util.i = 1;
                    }
                }
            }
        }
    }

}
