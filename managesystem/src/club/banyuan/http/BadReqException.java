package club.banyuan.http;

/**
 * 用户返回 400 请求的异常
 */
public class BadReqException extends RuntimeException {

  public BadReqException() {
  }

  public BadReqException(String message) {
    super(message);
  }

  public BadReqException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadReqException(Throwable cause) {
    super(cause);
  }

  public BadReqException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
