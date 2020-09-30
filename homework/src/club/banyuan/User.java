package club.banyuan;

import java.math.BigDecimal;

public class User {

    private Integer id;
    private String userName;
    private BigDecimal remainder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }

    public User(Integer id, String userName, BigDecimal remainder) {
        this.id = id;
        this.userName = userName;
        this.remainder = remainder;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", remainder=" + remainder +
                '}';
    }

}
