package club.banyaun.bank;

import java.util.concurrent.Semaphore;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 10:51 上午
 */
public class Count {

  private volatile double money = 1000;
  Semaphore semaphore=new  Semaphore(1);
  static Object lock = new Object();
  public double getMoney() {
    return money;
  }

  synchronized public void get() {
    money -= 200;
    System.out.println(Thread.currentThread().getName() + "取了200,还剩" + money);
  }


}
