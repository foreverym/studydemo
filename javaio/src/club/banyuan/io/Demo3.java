package club.banyuan.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/23 3:38 下午
 */
public class Demo3 {

  public static void main(String[] args) {
    File file = new File("javaio/1.txt");
    InputStream  inputStream=null;
    OutputStream fileOutputStream=null;
    try {
    if(!file.exists()){
      file.createNewFile();
    }
      inputStream =new FileInputStream(file); //生成一个文件流
      byte[] bytes = new byte[(int)file.length()];
     //inputStream.read(bytes);
      //int  ints=inputStream.read();
      inputStream.read(bytes, 0, (int)file.length());
      String  str=new String(bytes);
      System.out.println("---"+str);

      file = new File("javaio/2.txt");
      if(!file.exists()){
          file.createNewFile();
      }

      fileOutputStream = new FileOutputStream(file,true);
      fileOutputStream.write(str.getBytes());
      fileOutputStream.write("\n".getBytes());
      fileOutputStream.write(str.getBytes());
      fileOutputStream.write("\n".getBytes());
      fileOutputStream.write(str.getBytes());
      fileOutputStream.write("\n".getBytes());


    } catch (IOException e) {
      e.printStackTrace();
    }finally {

        try {
          if(fileOutputStream!=null){
            fileOutputStream.close();
          }
          if(inputStream!=null){
          inputStream.close();
          }

        } catch (IOException e) {
          e.printStackTrace();
        }

    }

  }

}
