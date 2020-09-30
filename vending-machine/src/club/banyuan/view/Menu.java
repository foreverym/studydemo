package club.banyuan.view;

import club.banyuan.domain.Goods;
import club.banyuan.domain.VendingMachine;

import java.util.List;

public class Menu {

    /**
     * 展示货品
     */
    public void showMenu(List<Goods> goodsList, int countPrice) {
        System.out.println("*---------------------------*\n" +
                "|     Vending   Machine     |\n" +
                "*---------------------------*\n" +
                "|   "+goodsList.get(0).getName() + "    " + goodsList.get(1).getName() + "    " + goodsList.get(2).getName() + "    " + goodsList.get(3).getName() + "    " + goodsList.get(4).getName() + "   |\n" +
                "|   "+"$"+goodsList.get(0).getPrice() + "  " + "$"+goodsList.get(1).getPrice() + "   " + "$"+goodsList.get(2).getPrice() + "   " + "$"+goodsList.get(3).getPrice() + "   " + "$"+goodsList.get(4).getPrice() + "  |\n" +
                "|   "+"["+goodsList.get(0).getFlag() + "]" + "   " + "["+goodsList.get(1).getFlag()+ "]" + "   " + "["+goodsList.get(2).getFlag()+ "]" + "   " + "["+goodsList.get(3).getFlag()+ "]" + "   " + "["+goodsList.get(4).getFlag()+ "]" + "  |\n" +
                "*---------------------------*\n" +
                "|                    [$ "+countPrice +"]  |\n" +
                "|                           |\n" +
                "|           [===]           |\n" +
                "*---------------------------*\n");
    }

    /**
     * 展示选择
     */
    public void showChoice() {
        System.out.println("What would you like to do?\n" +
                " 1. Read product information\n" +
                " 2. Insert coin\n" +
                " 3. Press product button\n" +
                " 4. Press return button\n" +
                " 9. Open service menu (code required)\n" +
                " 0. Quit");
        System.out.println();
        System.out.print("Your choice:");
    }


    public void displayProducts() {
        System.out.println("(1) The displayed products are:\n" +
                "A. Juice ($10)\n" +
                "B. Cola ($6)\n" +
                "C. Tea ($5)\n" +
                "D. Water ($8)\n" +
                "E. Coffee ($7)\n");
    }

    public void showInsertCoinView() {
        System.out.println("(2) Which coin would you like to insert?\n" +
                " 1. $1\n" +
                " 2. $2\n" +
                " 3. $5\n" +
                " 4. $10\n" +
                " 0. Go back");
        System.out.println();
        System.out.print("Your choice:");
    }

    public void showPressProductButtonView() {
        System.out.println("(3) Which product button would you like to press?\n" +
                " 1. A\n" +
                " 2. B\n" +
                " 3. C\n" +
                " 4. D\n" +
                " 5. E\n" +
                " 0. Go back");
        System.out.println();
        System.out.print("Your choice:");
    }

    public void showServiceMenu() {
        System.out.println("(9) Opening service menu. Access code is required.\n" +
                "Enter access code: 1110\n" +
                "Correct code!\n" +
                "\n" +
                "(9) What would you like to do?\n" +
                " 1. Inspect machine status\n" +
                " 2. Withdraw all money\n" +
                " 3. Refill product\n" +
                " 4. Change product\n" +
                " 0. Go back\n");
        System.out.println();
        System.out.print("Your choice:");
    }


    public void showMachineStatus(VendingMachine vendingMachine) {
        System.out.println("(9-1) Machine status\n" +
                "Amount of revenue: $6\n" +
                "Amount of inserted coins: $0\n" +
                "A. Juice ($10) (5 left)\n" +
                "B. Cola ($6) (sold out)\n" +
                "C. Tea ($5) (2 left)\n" +
                "D. Water ($8) (1 left)\n" +
                "E. Coffee ($7) (9 left)");
    }

    public void showRefillProductView() {
        System.out.println("(9-3) Which product would you like to refill?\n" +
                " 1. A\n" +
                " 2. B\n" +
                " 3. C\n" +
                " 4. D\n" +
                " 5. E\n" +
                " 0. Go back");
        System.out.println();
        System.out.print("Your choice:");
    }

    public void showChangeProductView() {
        System.out.println("(4-4) Which product would you like to change?\n" +
                " 1. A\n" +
                " 2. B\n" +
                " 3. C\n" +
                " 4. D\n" +
                " 5. E\n" +
                " 0. Go back");
    }


}
