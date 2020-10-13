package club.banyuan.http;

public enum RespStatus {
  OK("200 OK"),
  BAD_REQUEST("400 Bad request"),
  INTERNAL_SERVER_ERROR("500 Internal Server Error"),
  //
  ;

  private String msg;

  public String getMsg() {
    return "HTTP/1.1 " + msg + "\n";
  }

  RespStatus(String msg) {
    this.msg = msg;
  }
}
