package club.banyaun.practice;

public class Atm implements Runnable {

    BankCount bankCount;

    public Atm(BankCount bankCount) {
        this.bankCount = bankCount;
    }

    @Override
    public void run() {
        synchronized (Util.lock) {
            if (bankCount.getMoney() > 0) {
                if (bankCount.getFlag()) {
                    int money = bankCount.getMoney() - 200;
                    System.out.println("在" + Thread.currentThread().getName() + "上取钱");
                } else {
                    bankCount.setFlag(true);
                    System.out.println("账户正在使用中......");
                }
            }
        }
    }

}
