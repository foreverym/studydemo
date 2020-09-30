package club.banyaun;

public class Demo6 {

    private static Person person = new Person(new Phone("1999999", 0), "张三");

    public static void main(String[] args) {

        //Person personA = new Person(new Phone("1999999", 0), "张三");
        Person personB = new Person(new Phone("1993459", 0), "李四");
        Person personC = new Person(new Phone("1923999", 0), "王五");

        //Thread thread1 = new Thread(personA);
        Thread thread2 = new Thread(person);
        Thread thread3 = new Thread(person);

        thread2.start();
        thread3.start();

        if (thread2 == Thread.currentThread()) {

        }


        //


    }


}

