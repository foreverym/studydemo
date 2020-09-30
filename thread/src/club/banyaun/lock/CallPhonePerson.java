package club.banyaun.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/22 10:33 上午
 */
public class CallPhonePerson implements  Runnable{

  private final Person person;
  private Lock lock = new ReentrantLock();

  public CallPhonePerson(Person person) {
    this.person = person;
    new Thread(this).start();
  }
  public Person getPerson() {
    return person;
  }

  @Override
  public void run() {  //作为一个单独的线程启动
     while(true) {
       try {
         //person.semaphore.acquire(1); //一次只接受一个电话打进来....
           if (!person.isFlag()) {
             lock.lock();
               try {
                 System.out.println(getPerson().getName() + "正在等待接听中...");
                 person.wait();//当前线程一直在等待
               } catch (Exception e) {
                 e.printStackTrace();
               } finally {
                 lock.unlock();
               }
           }
       } catch (Exception e) {
         e.printStackTrace();
       }
       }
     }
  }


