package club.banyuan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * /**
 *  * 3.编写一个类Book，具有name,price,press,author 然后创建5个对象放入ArrayList中，并实现按照price大小排序，
 *  * 然后遍历ArrayList输出每个Book对象, 使用toString 方法打印
 *  *
 *  */

public class Demo3 {

    public static void main(String[] args) {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("海边的卡夫卡", 34, "江苏出版社", "村上春树"));
        bookList.add(new Book("白夜行", 56, "江苏出版社", "村上春树"));
        bookList.add(new Book("海子的诗", 67, "江苏出版社", "海子"));
        bookList.add(new Book("城南旧事", 89, "江苏出版社", "林徽因"));
        bookList.add(new Book("围城", 23, "江苏出版社", "钱钟书"));

        sortByPrice(bookList);

        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public static void sortByPrice(List<Book> list) {
        list.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
    }

}
