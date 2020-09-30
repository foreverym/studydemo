package club.banyaun.lock;

public class Demo3 extends Thread implements Runnable {

    @Override
    public void run() {
        System.out.println("kk");
    }

    public static void main(String[] args) {
        Thread demo3 = new Demo3();
        demo3.start();
    }

}
