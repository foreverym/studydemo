package club.banyaun.lock;

import java.util.concurrent.CountDownLatch;

public class Util {

    CountDownLatch countDownLatch = new CountDownLatch(1);

    public static int i = 0;

    static final Object lock = new Object();

}
