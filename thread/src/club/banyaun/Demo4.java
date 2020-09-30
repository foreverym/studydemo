package club.banyaun;

public class Demo4 implements Runnable {

    private int count = 100;

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println(Thread.currentThread().getName() + "----" + count);
            }
        }
    }
}


class Test4 {
    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();
        Thread thread1 = new Thread(demo4);
        Thread thread2 = new Thread(demo4);
        Thread thread3 = new Thread(demo4);
        Thread thread4 = new Thread(demo4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}