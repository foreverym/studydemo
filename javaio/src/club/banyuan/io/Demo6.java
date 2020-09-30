package club.banyuan.io;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Demo6 {

    public static void main(String[] args) {
        //将集合中的文件写入xml文件中
        //将xml文件中的数据，写到HTML页面中
        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("banyuan");



        OutputFormat format = OutputFormat.createCompactFormat();
        File file = new File("javaio/student.xml");

        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
