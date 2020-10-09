package club.banyuan.practice;

import java.io.*;

public class Decode {

  // /Users/liyi/works/repos/banyuan/JavaSE-2041/21.IO/practice/02.CaesarCipher/alice.code /Users/liyi/works/repos/banyuan/JavaSE-2041/21.IO/practice/02.CaesarCipher/alice.code.decode
  static String encodeFilePath = "/Users/liyi/works/repos/banyuan/JavaSE-2041/21.IO/practice/02.CaesarCipher/alice.code";
  static String decodeFilePath = "/Users/liyi/works/repos/banyuan/JavaSE-2041/21.IO/practice/02.CaesarCipher/alice.code.decode";

  public static void main(String[] args) throws IOException {
    BufferedReader inStream = null;
    BufferedWriter outStream = null;

    String inputFilePath = args[0];
    String outputFilePath = args[1];

    System.out.println(args[1]);
    System.out.println();

    System.out.println("输入文件：" + inputFilePath);
    System.out.println("输出文件：" + outputFilePath);
    // TODO
    //完成此部分代码，调用 caesarDecode 对传入的inputFilePath文件进行解密
    //将解密后的文本输出到 outputFilePath 文件中
    //尝试将alice.code进行解密
    FileReader fileReader = null;
    FileWriter fileWriter = null;
    try {
      fileReader = new FileReader(inputFilePath);
      fileWriter = new FileWriter(outputFilePath);
      inStream = new BufferedReader(fileReader);
      outStream = new BufferedWriter(fileWriter);

      int i = inStream.read();
      while (i != -1) {
        char c = (char) i;
        if (Character.isLetter((char) i)) {
           c = caesarDecode((char) i);

        }
        outStream.write(c);
        i = inStream.read();

      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        inStream.close();
        outStream.close();
        fileReader.close();
        fileWriter.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }


    System.out.println("解密成功！");
  }

  public static char caesarDecode(char ch) {
    if (Character.isUpperCase(ch)) {
      return (char) ((ch - Encode.FIRST_UPPER + Encode.NUM_CHARS - Encode.OFFSET) % Encode.NUM_CHARS
          + Encode.FIRST_UPPER);
    } else if (Character.isLowerCase(ch)) {
      return (char) ((ch - Encode.FIRST_LOWER + Encode.NUM_CHARS - Encode.OFFSET) % Encode.NUM_CHARS
          + Encode.FIRST_LOWER);
    } else {
      return ch;
    }
  }

}

