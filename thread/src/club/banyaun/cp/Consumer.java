package club.banyaun.cp;

import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private static int x = 0;
    private Mooncake mooncake;

    public Consumer(Mooncake mooncake) {
        this.mooncake = mooncake;
    }

    @Override
    public void run() {
        while (x < 50) {
            if (x % 2 == 0) {
                mooncake.setName("水果月饼");
                mooncake.setPrice(50.0);
            } else {
                mooncake.setName("蛋黄月饼");
                mooncake.setPrice(80.0);
            }
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x++;
            System.out.println("生产的🥮" + mooncake);
            //System.out.println(mooncake.hashCode());
        }
    }

    public static void main(String[] args) {
        Mooncake mooncake = new Mooncake(0.0, "");
        Consumer consumer = new Consumer(mooncake);

        Thread thread = new Thread(consumer);
        Thread thread1 = new Thread(consumer);

        thread.start();
        thread1.start();
    }
}
