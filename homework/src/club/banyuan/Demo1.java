package club.banyuan;

import java.util.*;

public class Demo1 {

    public static void main(String[] args) {
        List<String> fruitList = Arrays.asList("apple", "grape", "banana", "peer");
        List<String> list = sort(fruitList);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }



    }


    public static List<String> sort(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String fruit1, String fruit2) {
                return fruit1.compareTo(fruit2);
            }
        });
        return list;
    }



    public String findMax(List<String> list) {

        return list.get(list.size() - 1);
    }

    public String findMin(List<String> list) {
        return list.get(0);
    }

}
