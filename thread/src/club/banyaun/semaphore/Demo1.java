package club.banyaun.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Demo1 {

    public static void main(String[] args) {
        Runnable rb = new Runnable() {
            private Semaphore semaphore = new Semaphore(5);

            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + "结束");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            es.submit(rb);
        }

        //ThreadPoolUtils.stopPool(es);
    }
}
