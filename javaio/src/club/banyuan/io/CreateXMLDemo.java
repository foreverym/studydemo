package club.banyuan.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/24 3:05 下午
 */
public class CreateXMLDemo {
  /**
   *
   * 实现步骤：
   *
   * 第一步：创建一个Document实例
   *
   * `Document doc = DocumentHelper.createDocument();`
   * 第二步：先添加一个根结点，然后再添加子结点，构造成一个树形结构
   *
   * `Element root = doc.addElement("root");`
   * 第三步：添加xml文件样式（也可自定义样式），并输出xml文件到指定的路径下
   * `OutputFormat format = OutputFormat.createPrettyPrint();`
   *
   * `XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);`
   * `writer.write(doc);`
   */
  public static void main(String[] args) {
    Document document = DocumentHelper.createDocument();
    Element element = document.addElement("banYuan");
    Element element1 = element.addElement("employee");
    element1.addElement("empName")  //<employee ><empId  id="9527"></empId></employee>
        .addAttribute("id", "9527")
        .setText("李熠");

    element.addElement("employee")
        .addElement("empName")  //<employee ><empId  id="9527"></empId></employee>
        .addAttribute("id", "9528")
        .setText("林川");

    OutputFormat format = OutputFormat.createPrettyPrint(); //使用默认的样式创建xml文件
    //自定义样式
//    OutputFormat format = new OutputFormat();
//    format.setIndentSize(9);  // 行缩进
//    format.setNewlines(true); // 一个结点为一行
//    format.setTrimText(true); // 去重空格
//    format.setPadText(true);
//    format.setNewLineAfterDeclaration(false); // 放置xml文件中第二行为空白行
    File file=new File("JavaIO/banYuan.xml");
    OutputStream  outputStream=null;
    try {
      if(!file.exists()){
        file.createNewFile();
      }
      outputStream=  new FileOutputStream(file);
      //使用下面工具类对xml进行写入
      XMLWriter  xmlWriter=new XMLWriter(outputStream, format);
      xmlWriter.write(element);
      outputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
