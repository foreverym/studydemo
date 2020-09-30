package club.banyuan;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 8.自定义一个集合类,实现Iterator接口(hasNext和next方法),
 * 完成 contain(),isEmpty(),add(),addAll(),remove(),removeAll()方法
 *
 */
public class MyArrayList implements Iterator {


    private static final Object[] INSTANCE_LIST = {};

    public static void main(String[] args) {
        //INSTANCE_LIST = new Object[8];
        INSTANCE_LIST[1] = "DD";
        System.out.println(INSTANCE_LIST.length);
        List<String> list = new ArrayList<>();
        list.add("dd");

    }


    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }


}
