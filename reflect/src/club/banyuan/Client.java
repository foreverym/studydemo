package club.banyuan;

/**
 * 带有declare的方法表示在本类中定义的
 * 不带的表示可以获取父类中公共的
 */
public class Client {


    public static void main(String[] args) {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create(User.class);
        System.out.println(User.class.getName());
        System.out.println(user);
    }
}
