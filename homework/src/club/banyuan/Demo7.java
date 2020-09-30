package club.banyuan;

import java.util.*;

/**
 * 7. 请输入任意一个字符串,找出里面每个出现字符的次数
 */
public class Demo7 {

    public static void main(String[] args) {

    }

    public static Map<Character, Integer> findCount(String str) {
        Map<Character, Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        while (list.size() > 0) {
            for (int j = 1; j < list.size(); j++) {
                int k = 1;
                if (list.get(0).equals(list.get(j))) {
                    list.remove(j);
                    k++;
                }
                map.put(list.get(0), k);
            }
        }
        return map;
    }

}
