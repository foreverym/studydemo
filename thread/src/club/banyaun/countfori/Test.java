package club.banyaun.countfori;

public class Test {

    public static void main(String[] args) {
        Util util = new Util();
        CountThread countThread = new CountThread(util);
        DiscountThread discountThread = new DiscountThread(util);
        Thread threadA = new Thread(countThread);
        Thread threadB = new Thread(countThread);
        Thread threadC = new Thread(discountThread);
        Thread threadD = new Thread(discountThread);
        threadA.setName("A");
        threadA.start();
        threadB.setName("B");
        threadB.start();
        threadC.setName("C");
        threadC.start();
        threadD.setName("D");
        threadD.start();
    }
}
