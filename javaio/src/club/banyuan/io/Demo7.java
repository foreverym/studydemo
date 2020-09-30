package club.banyuan.io;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo7 {

    public static void main(String[] args) {
        Student student1 = new Student(1, "sss");
        Student student2 = new Student(2, "ddd");
        Student student3 = new Student(3, "kkk");
        Student student4 = new Student(4, "sss");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        Document document = DocumentHelper.createDocument();

        Iterator<Student> iterator = students.iterator();
        while(iterator.hasNext()) {
            Student next = iterator.next();
            document.addElement("student").
                    addAttribute("id", String.valueOf(next.getId())).
                    addAttribute("name", next.getName());
        }

        OutputFormat format = OutputFormat.createPrettyPrint(); //使用默认的样式创建xml文件

        File file = new File("javaio.xml");
        OutputStream outputStream = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            outputStream = new FileOutputStream(file);
            //其实就是利用字节输出流对其进行操作
            XMLWriter xmlWriter = new XMLWriter(outputStream, format);
            xmlWriter.write(document);
            //outputStream.write();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
