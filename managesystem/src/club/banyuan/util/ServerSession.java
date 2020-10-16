package club.banyuan.util;

import java.util.HashMap;
import java.util.UUID;

/**
 * session对象是一个Map，可以存放key value的对应关系
 * key 是String value是Object
 * session还包含一个唯一id
 */
public class ServerSession extends HashMap<String, Object> {

  // 在对象创建时生成唯一的字符串
  private String sessionId = UUID.randomUUID().toString();

  private boolean isValid = true;

  public String getSessionId() {
    return sessionId;
  }

  public void invalidate() {
    isValid = false;
  }

  public boolean isValid() {
    return isValid;
  }
}
