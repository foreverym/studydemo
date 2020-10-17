package club.banyuan.entity;

/**
 * username: 系统的登录用户名
 *                 id：唯一标识， 为1表示超级管理员
 *                 rolename: 角色，没有实际的作用，固定为 admin
 *                 password: 密码
 */
public class Admin {

    private Integer id;
    private String username;
    private String rolename;
    private String password;

    public Admin(Integer id, String username, String rolename, String password) {
        this.id = id;
        this.username = username;
        this.rolename = rolename;
        this.password = password;
    }

    public Admin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", rolename='" + rolename + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
