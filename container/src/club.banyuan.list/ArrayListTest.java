package club.banyuan.list;

import java.util.*;

public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("ssssssss");
        list.add("dddddddd");
        list.add("kkkkkkkk");


        Collections.sort(list);

        Iterator<String> iterator = list.iterator();
        Exception e = new ConcurrentModificationException("并发修改异常");
        try {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                list.add("ss");
            }

        } catch (ConcurrentModificationException e1) {
            e.printStackTrace();
            e.getMessage();
        }



//        Collections.sort((List<String> list1, (, s2) -> {
//            if (s1.length() > s2.length()) {
//
//            }
//        });

    }
}
