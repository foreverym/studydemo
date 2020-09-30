package club.banyuan.util;

import cn.hutool.core.util.StrUtil;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 4:27 下午
 */
public class Main {


  private Main(){

  }
  public static  void print(){
    System.out.println("我是你大爷...");
  }

  public static  int  getSum(int i,int j){
    return  i+j;
  }

  public static void main(String[] args) {
    System.out.println("现在只想赚钱,不想其他..");
    System.out.println(StrUtil.isNotBlank(""));
    System.out.println("要不是为了家人,活到30岁我都感觉多余...");
  }

  /**
   * 对咱们这个类进行打包操作(在src下操作的)
   *
   * 如果是手动编译  那么  自定义类引用的外部jar包也要编译进来
   * javac -cp .:/Users/sanye/2071/2071JavaSE/JavaSE2071/lib/hutool-all-4.0.11.jar club/banyuan/demo/Main.java -d  ../../out
   * 然后在命令行输入
   *   java -cp ../../out club.banyuan.demo.Main
   *   接着报错
   *    Caused by: java.lang.ClassNotFoundException: cn.hutool.core.util.StrUtil

      因为运行的时候也没有指定类路径
      最终的代码:
      java -cp ../../out:/Users/sanye/2071/2071JavaSE/JavaSE2071/lib/hutool-all-4.0.11.jar club.banyuan.demo.Main


   接着进行打包
   -C  表示在打包的时候不要out  不然out路径也包括进去了
   jar cfe main.jar club.banyuan.demo.Main -C ../../out club


    最中可以使用以下命令行单独执行jar  前提是有club.banyuan.demo.Main主类
   java -cp main.jar:/Users/sanye/2071/2071JavaSE/JavaSE2071/lib/hutool-all-4.0.11.jar club.banyuan.demo.Main


   */

}
