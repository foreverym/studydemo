package club.banyuan;

import java.util.*;

/**
 * 4.把如下元素存入List集合
 * “aaa” “bbb” “aaa” “abc”“xyz” “123” “xyz”去掉重复元素
 *
 */
public class Demo4 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa", "bbb", "aaa", "abc", "xyz", "123", "xyz");

        Set<String> set = removeSameData(list);

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static Set<String> removeSameData(List<String> list) {
        Set<String> set = new HashSet<>(list);
        return set;
    }

}
