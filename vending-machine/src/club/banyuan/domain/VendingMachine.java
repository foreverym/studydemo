package club.banyuan.domain;

import java.util.List;

public class VendingMachine {

    private Integer countCoin;
    private List<Goods> goodsList;

    public VendingMachine(Integer countCoin, List<Goods> goodsList) {
        this.countCoin = countCoin;
        this.goodsList = goodsList;
    }

    public Integer getCountCoin() {
        return countCoin;
    }

    public void setCountCoin(Integer countCoin) {
        this.countCoin = countCoin;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
