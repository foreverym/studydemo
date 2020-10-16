package club.banyuan.entity;

public class User {

  private int id;
  private String name;
  private String pwd;
  private String pwdConfirm;
  private int userType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPwdConfirm() {
    return pwdConfirm;
  }

  public void setPwdConfirm(String pwdConfirm) {
    this.pwdConfirm = pwdConfirm;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }

  public String getUserTypeStr() {
    if (userType == 0) {
      return "普通用户";
    } else if (userType == 1) {
      return "经理";
    }
    return "unknown";
  }


  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", pwd='" + pwd + '\'' +
        ", pwdConfirm='" + pwdConfirm + '\'' +
        ", userType=" + userType +
        '}';
  }
}
