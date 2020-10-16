package club.banyuan.http;

/**
 * 重定向异常，用来指导页面重定向
 */
public class RedirectException extends RuntimeException {

  public RedirectException() {
  }

  public RedirectException(String message) {
    super(message);
  }

  public RedirectException(String message, Throwable cause) {
    super(message, cause);
  }

  public RedirectException(Throwable cause) {
    super(cause);
  }

  public RedirectException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
