package club.banyuan.domain;

public class Goods {

    private String name;
    private Integer count;
    private Integer price;
    private String flag;

    public Goods(String name, Integer count, Integer price, String flag) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
