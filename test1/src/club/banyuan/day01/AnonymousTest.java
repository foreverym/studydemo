package club.banyuan.day01;

public class AnonymousTest {

    public static void find() {

    }

    /**
     * 静态成员内部类，可以直接在内部创建对象
     */
    static class Inner {
        public static int x = 0;
        public static void test() {
            AnonymousTest anonymousTest = new AnonymousTest();
            Inner inner = new Inner();
        }
    }

    class Inner2 {
        //static int y = 0; 成员内部类不可以定义静态变量和方法
//        public static void test1() {
//
//        }

        public void test() {

        }

    }

    public static void main(String[] args) {
        Inner2 inner2 = new AnonymousTest().new Inner2();
    }
}
