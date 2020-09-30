package club.banyaun.lock;

import java.util.concurrent.Semaphore;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/22 10:34 上午
 */
public class Person {

  private String  name;
  private  boolean flag =false ;//是否在打电话

  Semaphore semaphore = new Semaphore(1);//使用信号量的方式 一次只能接入一个

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }


}
