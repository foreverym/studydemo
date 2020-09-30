package club.banyuan.io;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Demo4 {

    public static void main(String[] args) {
        SAXReader saxReader = new SAXReader();
        File file = new File("javaio/student.xml");
        file.toURI();

        try {
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();

            Iterator<Element> elementIterator = rootElement.elementIterator();
            while (elementIterator.hasNext()) {
                Element next = elementIterator.next();

            }
            System.out.println(rootElement);
            System.out.println(rootElement.getName());
            List students = rootElement.elements();
            Iterator iterator = students.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                Element element = (Element) next;
                System.out.println(element.getName());
                element.getText();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
