package club.banyaun.cp;

public class Mooncake {

    private Double price;
    private String name;

    public Mooncake(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Mooncake() {

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mooncake{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
