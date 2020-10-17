package club.banyuan.entity;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String url;
    private String host;
    private Map<String,String> cookies;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> parseCookie(String cookieStr) {
        Map<String, String> cookies = new HashMap<>();
        String[] splitCookie = cookieStr.split(";");
        for (String keyValue : splitCookie) {
            String[] keyValuePair = keyValue.split("=");
            if (keyValuePair.length == 1) {
                cookies.put(keyValuePair[0], "");
            }
            else {
                cookies.put(keyValuePair[0], keyValuePair[1]);
            }
        }
        return cookies;
    }

}
