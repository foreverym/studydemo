package club.banyuan.serial;

import java.io.*;

public class Demo1 {

    public static void main(String[] args) {
        Student student = new Student(1, "小明");
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("javaio/1.txt"));
            objectOutputStream.writeObject(student);
            objectInputStream = new ObjectInputStream(new FileInputStream("javaio/1.txt"));
            Student student1 = (Student) objectInputStream.readObject();
            System.out.println(student1);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
