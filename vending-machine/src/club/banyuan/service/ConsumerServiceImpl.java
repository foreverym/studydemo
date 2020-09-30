package club.banyuan.service;

import club.banyuan.domain.Goods;
import club.banyuan.util.ScannerUtil;
import club.banyuan.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsumerServiceImpl {

    int countPrice = 0;
    List<Goods> goodsList = new ArrayList<>();

    {
        initialGoodsList();
    }

    Menu menu = new Menu();
    public void readProduct() {
        menu.showMenu(goodsList, 0);
        menu.displayProducts();
    }

    public void insertCoin() {
        menu.showInsertCoinView();
        String choiceNumStr = ScannerUtil.getInput();
        int caseNum = Integer.valueOf(choiceNumStr);
        System.out.println("Your choice:" + choiceNumStr);
        switch (caseNum) {
            case 1 :
                     System.out.println("You have inserted $1");
                     countPrice += 1;
                     helpInsertCoin();
                     break;
            case 2 :
                     System.out.println("You have inserted $2");
                     countPrice += 2;
                     helpInsertCoin();
                     break;
            case 3 :
                     System.out.println("You have inserted $5");
                     countPrice += 5;
                     helpInsertCoin();
                     break;
            case 4 :
                     System.out.println("You have inserted $10");
                     countPrice += 10;
                     helpInsertCoin();
                     break;
            case 0 :
                     menu.showMenu(goodsList, 0);
                     menu.showChoice();
                     ScannerUtil.getInput();
        }

    }

    public void pressProductButton() {
        menu.showPressProductButtonView();
        String input = ScannerUtil.getInput();
        Integer num = Integer.valueOf(input);
        if (num != 1 || num != 2 || num != 3 || num != 4 || num != 5 || num != 0) {
            System.out.println("输入错误");
        }
        switch (num) {
            case 1 :
                System.out.println("You have pressed button A.");
                int countA = goodsList.get(0).getCount();
                countA--;
                if (countA < 0) {
                    goodsList.get(0).setFlag("X");
                }
                countPrice -= goodsList.get(0).getPrice();
                goodsList.get(0).setCount(countA);
                break;
            case 2 :
                System.out.println("You have pressed button B.");
                int countB = goodsList.get(1).getCount();
                countB--;
                if (countB < 0) {
                    goodsList.get(1).setFlag("X");
                }
                countPrice -= goodsList.get(1).getPrice();
                goodsList.get(1).setCount(countB);
                break;
            case 3 :
                System.out.println("You have pressed button C.");
                int countC = goodsList.get(2).getCount();
                countC--;
                if (countC < 0) {
                    goodsList.get(2).setFlag("X");
                }
                countPrice -= goodsList.get(2).getPrice();
                goodsList.get(2).setCount(countC);
                break;
            case 4 :
                int countD = goodsList.get(3).getCount();
                countD--;
                if (countD < 0) {
                    goodsList.get(0).setFlag("X");
                }
                countPrice -= goodsList.get(3).getPrice();
                goodsList.get(3).setCount(countD);
                System.out.println("You have pressed button D.");break;
            case 5 :
                System.out.println("You have pressed button F.");
                int countF = goodsList.get(4).getCount();
                countF--;
                if (countF < 0) {
                    goodsList.get(4).setFlag("X");
                } else {
                    goodsList.get(4).setFlag("");
                }
                countPrice -= goodsList.get(4).getPrice();
                goodsList.get(4).setCount(countF);
                break;
            case 0 :
                System.out.println("You have pressed button A.");break;

        }
    }


    public void pressReturnButton() {
        countPrice = 0;
        menu.showMenu(goodsList, countPrice);
        menu.showChoice();
    }









    /**
     * 抽取insertCoin中的代码
     */
    public void helpInsertCoin() {
        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).getPrice() <= countPrice) {
                goodsList.get(i).setFlag("O");
            }
        }
        menu.showMenu(goodsList, countPrice);
        insertCoin();
    }

    public void initialGoodsList() {
        goodsList.add(new Goods("A", 10, 10, ""));
        goodsList.add(new Goods("B", 10, 6, ""));
        goodsList.add(new Goods("C", 10, 5, ""));
        goodsList.add(new Goods("D", 10, 8, ""));
        goodsList.add(new Goods("F", 10, 7, ""));
    }


}
