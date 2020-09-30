package club.banyuan.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo2 {

    public static void main(String[] args) {
        User user1 = new User(12, "sss", "pp");
        User user2 = new User(34, "ssskkk", "pp");
        User user3 = new User(12, "slss", "pkp");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        Stream<User> stream = userList.stream();
        //List<Boolean> collect = stream.map((user -> user.getName()+"")).collect(Collectors.toList());
        //for (User user : collect)
        stream.map((user) -> {
            if (user.getName().length() > 2) {

            }
            return userList;
        });
    }

}
