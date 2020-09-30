package club.banyuan.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class Demo1 {

    public static void main(String[] args) {

        File files = new File("javaio/banyuan");
        File[] filelist = files.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println(name);
                if (name.contains("/")) {
                    File files2 = new File(files.getPath() + "/" + "name");
                    File[] files1 = files2.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return false;
                        }
                    });
                    System.out.println("子目录的名字为：" + name);
                }
                //System.out.println(dir.getPath());
                if (name.endsWith(".jpg")) {

                    return true;
                } else {
                    return false;
                }
            }
        });

        for (File file : filelist) {
            System.out.println(file.getPath());
        }



    }

}
