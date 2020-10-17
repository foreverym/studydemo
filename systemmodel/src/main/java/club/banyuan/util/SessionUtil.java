package club.banyuan.util;

import club.banyuan.entity.ServerSession;

import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

    private Map<String, ServerSession> session = new HashMap();

    public void put(ServerSession serverSession) {
        session.put(serverSession.getSessionId(), serverSession);
    }

    public ServerSession get(String key) {
        return session.get(key);
    }

}
