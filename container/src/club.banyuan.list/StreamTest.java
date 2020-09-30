package club.banyuan.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("dd", "kk", "ll", "lkhhh", "kkkss"));
        Stream<String> stream = list.stream();
        List<String> collect = stream.filter(name -> name.length() > 2).collect(Collectors.toList());
        System.out.println(collect.get(0));

        //空指针异常, 拆箱与装箱的过程是什么
        Integer a = null;
        int b = a;
        System.out.println(a);

    }

}
