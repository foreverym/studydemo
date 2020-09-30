package club.banyaun.test;

public class Demo1 {

    static Object lock = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i + 1 % 2 == 0) {
                        System.out.print(i + 1 + "");
                    }
                }
            }
        }, "线程A");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 100; i++) {
                        if (i + 1 % 2 == 0) {
                            System.out.print(i + 1 + "");
                        }
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "线程B");

    }
}
