package club.banyaun.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/22 10:41 上午
 */
public class PhonePersonB extends Thread {

  private final CallPhonePerson callPhonePerson;
  private Lock lock = new ReentrantLock();

  public PhonePersonB(String name, CallPhonePerson callPhonePerson) {
    super(name);
    this.callPhonePerson = callPhonePerson;
  }

  @Override
  public void run() {
    while (true) {
      try {
        if (!callPhonePerson.getPerson().isFlag()) {

          lock.lock();
          callPhonePerson.getPerson().notify();//唤醒正在休眠的线程
          System.out.println(getName() + "正在拨号...");
          callPhonePerson.getPerson().setFlag(true);
          System.out.println(callPhonePerson.getPerson().getName()
              + "正在接入" + getName() + "的电话...");
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            lock.unlock();
          }
          System.out.println(callPhonePerson.getPerson().getName()
              + "与" + getName() + "的电话结束.....");

        } else {
          try {
            sleep(1000); //休息一下再打
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(callPhonePerson.getPerson().getName() + "正在通话中...B");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}
