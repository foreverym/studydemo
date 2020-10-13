package club.banyuan.util;

import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

  // sessionId 对应一个 ServerSession，每个ServerSession对应一个浏览器，
  // 将sessionId写入到浏览器的cookie中
  private static Map<String, ServerSession> serverSessionMap = new HashMap<>();


  public static ServerSession getSessionById(String sessionId) {
    return serverSessionMap.get(sessionId);
  }

  public static void put(String sessionId, ServerSession session) {
    serverSessionMap.put(sessionId, session);
  }

  public static void remove(String sessionId) {
    serverSessionMap.remove(sessionId);
  }
}
