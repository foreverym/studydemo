package club.banyaun.practice;

public class BankCount {

    private Boolean flag;
    private Integer money;

    public BankCount(Boolean flag, Integer money) {
        this.flag = flag;
        this.money = money;
    }

    public BankCount() {

    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "BankCount{" +
                "flag=" + flag +
                ", money=" + money +
                '}';
    }
}
