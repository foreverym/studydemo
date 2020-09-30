package club.banyaun.message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 11:25 上午
 */
public class MyThread  implements    Runnable {

  List<List<String>>  list;  //一个线程所执行的消息
  final Tool  tool;

  public MyThread(List<List<String>>  list,Tool tool) {
    this.list = list;
    this.tool=tool;
  }
  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    synchronized (tool) {
      System.out.println(Thread.currentThread().getName() + "发送的消息为:" + list.get(tool.anInt));
      tool.anInt++;
    }
  }
}
