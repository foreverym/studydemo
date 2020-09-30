package club.banyuan;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 6.编写一个程序，创建一个 HashMap对象，用于存储银行储户的信息(其中储户的主要信息有储户的ID，姓名和余额)。
 * 另外，计算并显示其中某个储户的当前余额
 */
public class Demo6 {

    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();

    }

    public BigDecimal getRemainder(String description, Map<String, User> map) {
        User user = map.get(description);
        BigDecimal remainder = user.getRemainder();
        return remainder;
    }

}
