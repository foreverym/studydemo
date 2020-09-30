package club.banyuan.day01;

import java.util.Arrays;

public class CopyArray {

    final int z = 0;
    static int k = 2;

    public int[] copyArray(int[] array1) {
        /**
         * 求数组中最大的三个数
         */
        int[] array = new int[0];
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < array1.length; j++) {
                if (array1[i] < array1[j]) {
                    int temp = array1[i];
                    array1[i] = array1[j];
                    array1[j] = temp;
                }
            }
        }
        for (int k = 0; k < array1.length; k++) {
            if (array1[k] > 4) {
                if (array == null) {
                    array[0] = array[k];
                }
                else {
                    array = Arrays.copyOf(array, array.length + 1);
                    array[array.length - 1] = array1[k];
                }
            }
        }
        return array1;
    }

    public String reverseString(String str) {
        String str1 = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            str1 = str1 + str.charAt(i);
        }
        return str1;
    }


    public static void main(String[] args) {
        int x;
        final int y;
        CopyArray copyArray = new CopyArray();
        int[] array = {4, 34, 8, 9, 0, 2, 4, 5, 74, 23, 25};
        int[] ints = copyArray.copyArray(array);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "     ");
        }

        String str2 = null;

        //对局部变量进行操作时需要进行初始化，直接赋值可不用
        //x = x + 2;

        System.out.println(str2 + "");

        System.out.println("、、、、、、、、、、、、、、、、、、、、、、、、、、、、");
        System.out.println(copyArray.reverseString("string"));
    }


}
