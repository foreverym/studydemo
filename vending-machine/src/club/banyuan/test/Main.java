package club.banyuan.test;

import club.banyuan.service.ConsumerServiceImpl;

public class Main {

    public static void main(String[] args) {
        ConsumerServiceImpl consumerService = new ConsumerServiceImpl();
        //consumerService.readProduct();
        consumerService.insertCoin();
    }
}
