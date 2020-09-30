package club.banyuan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = PropUtil.class.getResourceAsStream("/application.properties");
        System.out.println(PropUtil.class.getResource("/application.properties").getPath());
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        System.out.println(properties.get("path"));
        byte[] bytes = resourceAsStream.readAllBytes();
        System.out.println(new String(bytes));
        resourceAsStream.close();

    }
}
