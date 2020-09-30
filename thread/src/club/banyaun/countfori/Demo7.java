package club.banyaun.countfori;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有四个线程,其中A,B线程对i加1操作，C,D线程每次对i减1操作
 *
 * C   D  	   D--  C--
 *
 * A   B  		 A++  B++
 *
 * 而不是这样:
 * A++  D--  C--  B++
 * D--  B++  A++  C--
 */
public class Demo7 implements Runnable {

    //为了解决高并发的问题   CountDownLatch  类似于一个线程计数器  使一个线程或者多个线程完成各自的工作然后再继续执行
    CountDownLatch countDownLatch=new CountDownLatch(2);// 2表示有两个线程计数器

    private int i;
    private String threadName;
    private static Thread next;
    private static final Object lock = new Object();

    public Demo7(int i, String threadName) {
        this.i = i;
        this.threadName = threadName;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {

        synchronized (lock) {

            if (Thread.currentThread().getName().equals("A") || Thread.currentThread().getName().equals("B")) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Thread.currentThread().getName().equals("C") || Thread.currentThread().getName().equals("D")) {
                }

                i++;

            }
        }
    }

}
