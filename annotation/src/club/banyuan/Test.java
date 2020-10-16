package club.banyuan;

public class Test {

    @MyAnnotation(messageStr = MyAnnotation.message.MESSAGE)
    public void test() {
        System.out.println();
    }
}
