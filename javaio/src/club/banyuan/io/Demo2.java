package club.banyuan.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo2 {

    public static void main(String[] args) {

        Student student1 = new Student(1, "sss");
        Student student2 = new Student(2, "ddd");
        Student student3 = new Student(3, "kkk");
        Student student4 = new Student(4, "sss");
        List<Student> students = new ArrayList<>();
//        students.add(student1);
//        students.add(student2);
//        students.add(student3);
//        students.add(student4);

        File file = new File("/Users/edz/IdeaProjects/wybtest/1.txt");
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;

        System.out.println(file.getPath());

        try {
            if (!file.exists()) {
                System.out.println("文件不存在");
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStream.read(bytes, 0, (int)file.length());
            String str = new String(bytes);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
