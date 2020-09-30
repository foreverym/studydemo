package club.banyaun.bank;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 10:51 上午
 */
public class Demo {

  /**
   *  2.有一个银行账户,可以在柜台取钱，也可以在ATM取钱,
   *     只要在其中一个取钱,另一个就会接收消息,账户正在使用中....
   *     每次取200,AMT和柜台交替取钱,总共取1000
   */
  public static void main(String[] args) {

    Count  count=new Count();
    PersonA personA = new PersonA(count);
    new Thread(personA,"柜台").start();
    new Thread(personA,"ATM").start();

  }

}
