package club.banyaun;

public class Demo5 implements Runnable {

    private static int count = 100;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                count--;
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + "+++" + count);
                } else {
                    break;
                }
            }
        }
    }

}


class Test5 {
    public static void main(String[] args) {
        Demo5 demo5 = new Demo5();
        Thread thread1 = new Thread(demo5);
        Thread thread2 = new Thread(demo5);
        Thread thread3 = new Thread(demo5);
        Thread thread4 = new Thread(demo5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}