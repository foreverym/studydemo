package club.banyuan;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ReflectDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输入类的全限定类名
        String line = scanner.nextLine();
        try {
            Class<?> aClass = Class.forName(line);
            Object o = aClass.getConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
