package club.banyuan.optional;

import java.util.Optional;

/**
 * Optional类解决空指针异常
 */
public class Demo1 {

    private static boolean u;

    public static void main(String[] args) {
        Optional<Student> student1 = Optional.ofNullable(null);
        //student1.isPresent(u -> System.out.println());
        //student1.isPresent(u -> System.out.println(Demo1.u));
        Student student = null;
        Student defaultStudent = new Student(1, "");
        defaultStudent.setId(1);
        defaultStudent.setName("————undefinedNAME-----");
        Student player1 = Optional.ofNullable(student).orElse(defaultStudent);
        System.err.println(player1.getName());
    }

}
