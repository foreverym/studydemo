package club.banyaun.bank;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 10:55 上午
 */
public class PersonA implements  Runnable{
  final Count count;

  public PersonA(Count count) {
    this.count = count;
  }

  @Override
  public void run() {
    while(true){
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      try {
//        count.semaphore.acquire(1);
//
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }

      synchronized (Count.lock) {

        //if (Thread.currentThread().getState() == Thread.State.WAITING)
        if (count.getMoney() > 0) {
          count.get();
//          //count.semaphore.release(1);
//          try {
//            Count.lock.wait();
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
        } else {
          try {
            throw new Exception("账户没钱了");
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        }
      }
    }
  }
}
