package club.banyaun.lock;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Demo2 {

    public static final ConcurrentHashMap<String,String> firstHashMap = new ConcurrentHashMap<>();
    public static void main(String[] args) throws InterruptedException{
        //线程一
        Thread t1 = new Thread(){
            public void run(){
                for(int i = 0;i<50;i++){
                    firstHashMap.put(String.valueOf(i), String.valueOf(i));
                }
            }
        };

        //线程二
        Thread t2 = new Thread(){
            public void run(){
                for(int j = 50;j<100;j++){
                    firstHashMap.put(String.valueOf(j), String.valueOf(j));
                }
            }
        };

        t2.start();
        t1.start();

        //主线程休眠1秒钟，以便t1和t2两个线程将firstHashMap填装完毕。
        Thread.currentThread().sleep(1000);
        for(int l =0;l <100;l++){
            //如果key和value不同，说明两个线程在put的过程出现异常
            if(!String.valueOf(l).equals(firstHashMap.get(String.valueOf(l)))){
                System.out.println(String.valueOf(l)+": "+firstHashMap.get(String.valueOf(l)));
            }
        }

    }
}
