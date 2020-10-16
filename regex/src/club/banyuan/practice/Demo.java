package club.banyuan.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的编写
 */
public class Demo {

    public static void main(String[] args) {

        //练习1
        System.out.println("222".matches("(0|1)+"));

        //练习2
        System.out.println("00001110".matches("[01]*0"));

        //练习3
        System.out.println("".matches("([01]{2})*"));


        //练习4
        System.out.println("00001110".matches("(pickup truck)|(pick up truck)|(pick-up truck)"));

        //练习5
        System.out.println("ff hh".matches("((\\w{1,}\\s){2}(\\w{1,})(\\s\\w{1,}))|(\\w{1,}\\s){2}(\\w{1,})"));

        //练习6
        System.out.println("$67.99".matches("\\$(([0].[0-9]{0,2})|([1-9]{1,}.[0-9]{1,}))"));
    }

}
