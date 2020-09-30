package club.banyuan.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.add("dd");

        list.add("123");
        list.add("345");
        list.add("789");
        list.add("321");
        //System.out.println(Thread.currentThread().getName());
        Stream<String> stream = list.stream();
        List<String> strings = Arrays.asList("dd", "mm", "kk");

        Predicate<String> predicate = (str) -> str.length() > 4;

        stream.filter(
                predicate
        ).forEach(System.out::println);

        stream.skip(2).forEach(System.out::println);

        //map操作
        Stream<String> stringStream = stream.map(String::toUpperCase);
        //降序操作
        stream.sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 01 - 02;
            }
        });

        Stream<String> stringStream1 = list.parallelStream();
        Optional<String> first = stream.findFirst();

        List<String> collect = stream.skip(2).collect(Collectors.toList());


    }


}
