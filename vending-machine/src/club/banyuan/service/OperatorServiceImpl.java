package club.banyuan.service;

import club.banyuan.domain.Goods;
import club.banyuan.domain.VendingMachine;
import club.banyuan.util.ScannerUtil;
import club.banyuan.view.Menu;

import java.util.List;

public class OperatorServiceImpl {


    Menu menu = new Menu();
    VendingMachine vendingMachine = null;

    public void initialVendingMachine(Integer countCoin, List<Goods> goodsList) {
        vendingMachine = new VendingMachine(countCoin, goodsList);
    }

    public void getMachineStatus() {
        menu.showMachineStatus(vendingMachine);
    }

    public void withdrawAllMoney() {
        System.out.println("(9-2) All money is being withdrawn.\n");
        System.out.println(vendingMachine.getCountCoin() + " is withdrawn.");
    }

    public void refillProduct() {
        menu.showRefillProductView();
        String input = ScannerUtil.getInput();
        int num = Integer.valueOf(input);
        switch (num) {
            case 1 :
                vendingMachine.getGoodsList().get(0).setCount(10);
                System.out.println("You have refilled product A to full.");
            case 2 :
                vendingMachine.getGoodsList().get(1).setCount(10);
                System.out.println("You have refilled product B to full.");
            case 3 :
                vendingMachine.getGoodsList().get(2).setCount(10);
                System.out.println("You have refilled product C to full.");
            case 4 :
                vendingMachine.getGoodsList().get(3).setCount(10);
                System.out.println("You have refilled product D to full.");
            case 5 :
                vendingMachine.getGoodsList().get(4).setCount(10);
                System.out.println("You have refilled product E to full.");
            case 0 :
                vendingMachine.getGoodsList().get(1).setCount(10);
                System.out.println("You have refilled product B to full.");
        }

    }

    public void changeProduct() {
        menu.showChangeProductView();
        String input = ScannerUtil.getInput();
        int num = Integer.valueOf(input);
        switch (num) {
            case 1 :
                System.out.println("You are changing product A.");
            case 2 :
                System.out.println("You are changing product B.");
            case 3 :
                System.out.println("You are changing product C.");
            case 4 :
                System.out.println("You are changing product D.");
            case 5 :
                System.out.println("You are changing product E.");
            case 0 :
                System.out.println("You are changing product E.");

        }

        System.out.print("Enter new product name:");
        String inputName = ScannerUtil.getInput();
        System.out.print("Enter new product price:");
        String inputPrice = ScannerUtil.getInput();
        int price = Integer.valueOf(inputPrice);

        vendingMachine.getGoodsList().get(num).setName(inputName);
        vendingMachine.getGoodsList().get(num).setPrice(price);
    }


}
