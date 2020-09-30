package club.banyuan.list;

import java.text.SimpleDateFormat;
import java.util.*;

public class HashSetTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ff", "mm", "kk", "ll", "mm");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(System.currentTimeMillis());

        System.out.println(format);

        Set<String> set = new HashSet<>(list);

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

}
