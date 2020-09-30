package club.banyaun.practice;

import java.time.chrono.ThaiBuddhistChronology;

/**
 *2. 修改ReverseHelloMultithreaded文件，
 * 创建线程（我们将其称为线程1）。线程1创建另一个线程（线程2）；线程2创建线程3；依此类推，直到线程50。
 * 每个线程都应打印 "Hello from Thread \<num\>!"。完成后，保证ReverseHelloTest应成功运行，如下
 * ```
 * Hello from thread 50
 * Hello from thread 49
 * Hello from thread 48
 * Hello from thread 47
 * ... 省略
 * Hello from thread 2
 * Hello from thread 1
 * ```
 */
public class ReverseHelloMultithreaded {

    static Object lock = new Object();
    static int count = 50;

    public static void doReverseHello() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("Hello from thread " + count--);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count > 0) {
                        doReverseHello();
                    }
                }
            }
        }).start();

    }
}
