package club.banyuan.list.club.banyuan.map;

import club.banyuan.list.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

    //
    private static final int NAME = 4;

    public static void main(String[] args) {


        Map<String, String> map = new HashMap<>();
        map.put("email", "12345");
        map.put("phone", "12345677");

        Set<Map.Entry<String, String>> entries = map.entrySet();

    }

}
