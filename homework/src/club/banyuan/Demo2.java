package club.banyuan;

import java.util.*;

/**
 * 2.分析以下需求，并用代码实现：
 * (1)生成10个1至100之间的随机整数(不能重复)，存入一个List集合
 * (2)编写方法对List集合进行排序
 * (2)然后利用迭代器遍历集合元素并输出
 * (3)如：15 18 20 40 46 60 65 70 75 91
 */
public class Demo2 {

    public static void main(String[] args) {

    }

    public List<Integer> getRandomList() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; list.size() < 10; i++) {
            int num = random.nextInt(100);
            if (!list.contains(num)) {
                list.add(num);
            }
        }
        return list;
    }

    public List<String> sortList(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return list;
    }

    public void printList(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
