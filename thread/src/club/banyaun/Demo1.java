package club.banyaun;

public class Demo1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i< 123; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            Thread.yield(); //在某个时间片段，有

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Test {
    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1Thread = new Demo1();
        Thread thread1 = new Thread(demo1Thread);
        thread1.start();


        Thread thread3 = new Thread(demo1Thread, "线程2----");
        thread3.start();

        thread3.join();



        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread2.start();


    }
}