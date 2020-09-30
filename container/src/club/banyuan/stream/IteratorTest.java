package club.banyuan.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {
        User user1 = new User(12, "sss", "pp");
        User user2 = new User(34, "ssskkk", "pp");
        User user3 = new User(12, "slss", "pkp");
        try {
            Class<?> aClass = Class.forName("club.banyuan.stream.StreamDemo2");
            try {
                Object o = aClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        Iterator<User> iterator = userList.iterator();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getName().length() > 3) {
                userList.add(user3);
            }
        }
        //迭代器
        System.out.println(userList.size());
    }
}
