package club.banyuan.homework;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Demo3 {

    public static void main(String[] args) {
        String s = "km.mm";
        System.out.println(s.lastIndexOf("."));
        System.out.println(s.length());
        String substring = s.substring(s.lastIndexOf(".")+1, s.length());
        System.out.println(substring);
        File file = new File("");
        File[] files = new File[2];
        files[0] = file;
        //List<File> files = new ArrayList<>();
        //files.add(file);
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                String o1Name = o1.getName();
                String o2Name = o2.getName();
                o1Name = o1Name.substring(o1Name.lastIndexOf("."), o1Name.length()) ;
                o2Name = o2Name.substring(o2Name.lastIndexOf("."), o2Name.length()) ;
                return (Integer.valueOf(o1Name) - Integer.valueOf(o2Name));

            }
        });
    }
}
