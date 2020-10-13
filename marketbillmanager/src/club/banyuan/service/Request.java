package club.banyuan.service;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String data;

    private Map<String, String> cookies;

    private static Map<String, Map> session;

    public void main(String[] args) {
        cookies = new HashMap<>();
        cookies.remove("key");
    }

}
