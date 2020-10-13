package club.banyuan.entity;

import java.math.BigDecimal;

/**
 * {
 *   "id": "",
 *   "providerId": "1",
 *   "money": "1000",
 *   "product": "购买一件商品",
 *   "isPay": "1"
 * }
 */
public class Bill {

    private Integer id;
    private Integer providerId;
    private BigDecimal money;
    private String product;
    private Integer isPay;
    private String providerName;
    private String updateTime;

    public Bill(Integer id, Integer providerId, BigDecimal money, String product, Integer isPay, String providerName, String updateTime) {
        this.id = id;
        this.providerId = providerId;
        this.money = money;
        this.product = product;
        this.isPay = isPay;
        this.providerName = providerName;
        this.updateTime = updateTime;
    }

    public Bill() {
    }


    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", providerId=" + providerId +
                ", money=" + money +
                ", product='" + product + '\'' +
                ", isPay=" + isPay +
                ", providerName='" + providerName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

}
