package test.club.banyuan;

import club.banyuan.service.ConsumerServiceImpl;
import org.junit.Test;

public class VendingMachineTest {

    ConsumerServiceImpl consumerService = new ConsumerServiceImpl();
    @Test
    public void test() {
        consumerService.insertCoin();
    }
}
