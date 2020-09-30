package club.banyaun.message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 11:25 上午
 */
public class Demo {
  /**
   *  3.多线程实现任务分发----向1W用户发送祝福短信
   *     1000条
   *     10线程
   *     发给用户
   */
  public static void main(String[] args) {
    Tool tool = new Tool();
    List<String> init = init();//得到所有信息
    int   thread_Number=init.size()/10;
    List<List<String>> lists=new ArrayList<>();



    for (int i = 0; i <10; i++) {
      List<String>  list=new ArrayList<>();
      for (int j = 0; j < init.size(); j++) {
        int   index=(j+1)/thread_Number;  //  1~999  都在1000里面
        if(index==i){
          list.add(init.get(j));
        }
      }
      lists.add(list);
    }

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    MyThread myThread = new MyThread(lists,tool);
    executorService.submit(new  Thread(myThread,"线程A"));
    executorService.submit(new  Thread(myThread,"线程B"));
    executorService.submit(new  Thread(myThread,"线程C"));
    executorService.submit(new  Thread(myThread,"线程D"));
    executorService.submit(new  Thread(myThread,"线程E"));
    executorService.submit(new  Thread(myThread,"线程F"));
    executorService.submit(new  Thread(myThread,"线程G"));
    executorService.submit(new  Thread(myThread,"线程H"));
    executorService.submit(new  Thread(myThread,"线程I"));
    executorService.submit(new  Thread(myThread,"线程H"));
    executorService.submit(new  Thread(myThread,"线程I"));
    executorService.submit(new  Thread(myThread,"线程J"));



//    new  Thread(myThread,"线程A").start();
//    new  Thread(myThread,"线程B").start();
//    new  Thread(myThread,"线程C").start();
//    new  Thread(myThread,"线程D").start();
//    new  Thread(myThread,"线程E").start();
//    new  Thread(myThread,"线程F").start();
//    new  Thread(myThread,"线程G").start();
//    new  Thread(myThread,"线程H").start();
//    new  Thread(myThread,"线程J").start();
//    new  Thread(myThread,"线程K").start();






  }
  public  static  List<String>  init(){
    List<String>  list=new ArrayList<>();
    for (int i = 0; i <10000; i++) {
      list.add("半圆:"+i);
    }
    return list;
  }
}
