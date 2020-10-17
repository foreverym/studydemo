package club.banyuan.entity;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ServerSession extends HashMap<String, Object> {

    private String sessionId = UUID.randomUUID().toString();
    private Boolean isValid;

    public String getSessionId() {
        return sessionId;
    }



}
