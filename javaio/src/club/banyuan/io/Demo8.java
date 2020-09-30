package club.banyuan.io;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Demo8 {

    public static void main(String[] args) {

        SAXReader saxReader = new SAXReader();
        File file = new File("javaio/test.html");
        InputStream inputStream = null;

        try {
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();
            Iterator<Element> elementIterator = rootElement.elementIterator();

            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            inputStream.read(bytes);
            String str = new String(bytes);
            System.out.println(str);
            str.replaceAll("####", "<table>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
