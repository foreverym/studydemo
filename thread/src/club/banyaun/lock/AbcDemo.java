package club.banyaun.lock;

public class AbcDemo {

    public static void main(String[] args) {

        Util util = new Util();

        AThread aThread = new AThread(util);
        BThread bThread = new BThread(util);
        CThread cThread = new CThread(util);

        Thread thread1 = new Thread(aThread);
        Thread thread2 = new Thread(bThread);
        Thread thread3 = new Thread(cThread);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
