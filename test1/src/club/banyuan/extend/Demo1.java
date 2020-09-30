package club.banyuan.extend;

public class Demo1 {

    static boolean flag;

    public static void main(String[] args) {
        System.out.println(flag);
        Animal animal = new Animal();
        animal = new Rabbit();
    }

}
