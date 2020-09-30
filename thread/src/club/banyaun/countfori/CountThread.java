package club.banyaun.countfori;

import java.util.concurrent.CountDownLatch;

public class CountThread implements Runnable {

    private Util util;

    public CountThread(Util util) {
        this.util = util;
    }



    @Override
    public void run() {
        while (true) {
            synchronized (util) {
                if (util.flag) {
                    if (util.countDownLatch.getCount() > 0) {
                        util.countDownLatch.countDown();
                        util.i--;
                        System.out.println(Thread.currentThread().getName() + "进行了操作");
                    } else {
                        try {
                            util.countDownLatch = new CountDownLatch(2);
                            util.countDownLatch.await();
                            util.flag = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
