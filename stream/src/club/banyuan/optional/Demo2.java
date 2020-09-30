package club.banyuan.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo2 {

    public static void main(String[] args) {
        Student student1 = new Student(1, "sss");
        Student student2 = new Student(2, "ddd");
        Student student3 = new Student(3, "kkk");
        Student student4 = new Student(4, "sss");
        Student student5 = new Student(1, "sss");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        //函数型接口
        Function<List<Student>, List<Student>> function = new Function<List<Student>, List<Student>>() {
            @Override
            public List<Student> apply(List<Student> s) {
                List<Student> list = new ArrayList<>();
                for (Student student : s) {
                    if (student.getName().equals("sss")) {
                        list.add(student);
                    }
                }
                return list;
            }
        };

        List<Student> selectStudents = function.apply(students);
        for (Student student : selectStudents) {
            System.out.println(student);
        }
        System.out.println("-------------------------------------------------------");

        //供应型接口
        Supplier<Student> supplier = new Supplier<Student>() {
            @Override
            public Student get() {
                return new Student(3, "dd");
            }
        };
        Student student = supplier.get();
        System.out.println(student);
        System.out.println("-------------------------------------------------------");


        //消费型接口
        Consumer<List<Student>> consumer = new Consumer<List<Student>>() {
            @Override
            public void accept(List<Student> students) {
                for (Student student : students) {
                    if (student.getId() > 2) {
                        System.out.println(student);
                    }
                }
            }
        };
        consumer.accept(students);
        System.out.println("-------------------------------------------------------");

        //断言型接口
        Predicate<List<Student>> predicate = new Predicate<List<Student>>() {
            @Override
            public boolean test(List<Student> students) {
                if (students.size() > 2) {
                    return true;
                }
                return false;
            }
        };
        System.out.println("-------------------------------------------------------");

        //创建无限流
        List<Integer> list = Stream.iterate(0, i -> i + 1).limit(5).collect(Collectors.toList());
        for (Integer num : list) {
            System.out.println(num);
        }

        students.stream().distinct().forEach(System.out::println);
        System.out.println("-------------------------------------------------------");

        students.stream().filter(studentf -> studentf.getId()>2).forEach(System.out::println);

        students.stream().map(studentm -> studentm.getName() + "欢迎你").forEach(System.out::println);

        Integer[] integers = {1, 3, 4, 5, 2, 9};
        List<Integer> integerList = Arrays.asList(integers);
        integerList.stream().mapToLong(k -> k+997);


    }



}
