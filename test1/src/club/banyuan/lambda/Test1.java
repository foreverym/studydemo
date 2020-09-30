package club.banyuan.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class Test1
{

    public static void main(String[] args) {
        /**
         * 函数式接口
         */
        Function<String, Boolean> function = (name)->{
            if (name.equals("张三")) {
                return true;
            }
            return false;
        };

        boolean isname = function.apply("张三");

        /**
         * 消费型接口
         */
        Consumer<Double> consumer = (money) -> {
            System.out.println("消费了：" + money);
        };

        consumer.accept(890.0);

        /**
         * 供给型接口
         */
        Supplier<User> stringSupplier = () -> new User(12, "小明");
        System.out.println(stringSupplier.get().toString());

        /**
         * 断言型接口
         */

    }



}


class User {
    public String username;
    public int age;

    public User(int age, String username){
        this.age = age;
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}