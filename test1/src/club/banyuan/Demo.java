package club.banyuan;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Demo {

    /**
     * 作业: BiFunction：定义 R apply(T t, U u) 抽象方法，接收t和u参数，返回R对象，如果需要两个对象中的某些值来组装成另一个对象，
     * 可以使用。我把它看成Function的神级版 public static <T, U, R> R biFunction(T t,U u,BiFunction<T, U, R>
     * biFunction){ return biFunction.apply(t, u); }
     * <p>
     * <p>
     * <p>
     * BiConsumer：定义了 void accept(T t, U u) 抽象方法，我把它看成Consumer的神级版 public static <T, U> void
     * biConsumer(T t, U u, BiConsumer<T, U> biConsumer) { biConsumer.accept(t, u); }
     * <p>
     * <p>
     * <p>
     * lambda函数引用规则:
     */
    public static void main(String[] args) {
        Consumer<String> c1 = r -> System.out.println(Integer.parseInt(r));
        c1.accept("1");
        Function<String, Integer> c2 = Integer::parseInt;  //Integer

        Consumer<Integer> c3 = System.out::println;
        Integer sum = sum(c2, "43");
        c3.accept(sum);

        System.out.println("》》》》》》》》》》》》》》》》》》》");

        String inStr = "lambda add power to Java";

        Function<String, String> strReverse = MyStringOps::strReverse;    //静态方法的引用
        String st = strReverse.apply(inStr);
        Consumer<String> consumer = System.out::println;
        consumer.accept("输出内容为：" + st);

        // StringFunc   stringFunc=   (str)->MyStringOps.MyStringOps(str);
        StringFunc stringFunc = MyStringOps::strReverse;
        System.out.println(stringFunc.func("张三"));  //将字符串传递给指定的类里面的指定的方法  如:MyStringOps::strReverse

        //MyStringOps::strReverse 相当于实现了接口方法func() ，并在接口方法func()中作了MyStringOps.strReverse()操作
        String outStr = stringOp(MyStringOps::strReverse, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);

        BiFunction<Integer, Integer, Integer> biFunction = Math::max;// 前面两个是参数类型 最后一个是返回值类型
        System.out.println(biFunction.apply(123, 543));

        StringFunc stringFunc1 = String::trim;
        System.out.println(":" + stringFunc1.func(" ashdaskhdashk ") + ":");

//    Consumer<String[]> main = Demo::main;  //java.lang.StackOverflowError
//    main.accept(new String[]{"张三","李四"});

        MyStringOps stringFunc2 = new MyStringOps();
        StringFunc getString = stringFunc2::getString;  //对象名::方法名  这个方法是成员方法不是静态方法
        System.out.println(getString.func("asdaw"));

//    Consumer<String> n1 = r ->new BigDecimal(r);
//    n1.accept("1");
//    Consumer<String> n2 =BigDecimal::new;
//    n2.accept("2");

    }


    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }


    public static Integer sum(Function<String, Integer> c2, String string) {
        return c2.apply(string) + 34;
    }


}

@FunctionalInterface
interface StringFunc {

    String func(String n);
}


class MyStringOps {


    //静态方法： 反转字符串
    public static String strReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }


    public String getString(String string) {

        return string.toUpperCase();
    }

}

