package club.banyaun.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();

    public void testLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + lock.tryLock());
        } finally {
            lock.unlock();
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "锁被释放了");
        }
    }
}

class MyThread2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
