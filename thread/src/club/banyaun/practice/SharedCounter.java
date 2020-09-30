package club.banyaun.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>这个方法应该启动numThreads数量的线程，
 *  并且每个线程都应该向counter中增加numIncrementsPerThread的值。</p>
 */
public class SharedCounter {

  static int counter = 0;


  public static void reset() {
    counter = 0;
  }

  /**
   *
   * @param numThreads
   * @param numIncrementsPerThread
   * @return
   * @throws InterruptedException
   */
  public static int increment(int numThreads, int numIncrementsPerThread) throws InterruptedException {
    for (int i = 0; i < numThreads; i++) {
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          counter += numIncrementsPerThread;
        }
      });
    }
    return counter;
  }



}
