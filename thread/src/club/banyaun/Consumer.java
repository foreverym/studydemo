package club.banyaun;

public class Consumer implements Runnable {

    private Shop shop;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            show();
        }
    }

    public Consumer(Shop shop) {
        this.shop = shop;
    }

    public synchronized void show() {
        int count = shop.getInventory();
        count--;
        shop.setInventory(count);
        System.out.println(Thread.currentThread().getName() + "购买了商品，还剩下" + count);
    }

    public static void main(String[] args) {
        Shop shop = new Shop(100);
        Consumer consumer = new Consumer(shop);
        Thread thread1 = new Thread(consumer);
        Thread thread2 = new Thread(consumer);
        thread1.setName("买家一");
        thread2.setName("买家二");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
