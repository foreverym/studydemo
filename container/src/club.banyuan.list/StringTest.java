package club.banyuan.list;

public class StringTest {

    public static void main(String[] args) {
        String str = "h34,345,567,789,234";
        String[] split = str.split(",");
        for (int i = 0; i< split.length; i++) {
            System.out.println(split[i]);
        }
    }
}
