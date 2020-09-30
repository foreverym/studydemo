package club.banyuan.homework;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {



    /**
     * @param sourceFile   目标文件
     * @param size         拆分文件大小 byte为单位
     * @param targetFolder 生成的拆分文件路径，如果传入的路径不存在，则创建这个路径
     *                     拆分文件的命名规则，原始文件名.编号。
     *                     例如 WIN.mp3 拆分后，变为 WIN.mp3.1  WIN.mp3.2 ....
     */
    public static void split(File sourceFile, int size, File targetFolder) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        List<byte[]> list = new ArrayList<>();

        try {
            if (sourceFile.exists()) {
                sourceFile.createNewFile();
            }
            inputStream = new FileInputStream(sourceFile);
            //获得文件的长度
            long length = sourceFile.length();

            //inputStream.read(bytes, 1, );
            for (int i = 1; i <= length-1/1024 + 1; i++) {
                //inputStream.read()
                byte[] bytes = new byte[1024];
                list.add(bytes);
            }

            System.out.println(list);

            for (int j = 0; j < list.size(); j++) {
                if (j >= list.size()) {
                    inputStream.read(list.get(j), j*1024, (int)sourceFile.length());
                } else {
                    inputStream.read(list.get(j), j*1024, (j+1) * 1024);
                }
                File file = new File("javaio/WIN.mp3." + j);
                if (!file.exists()) {
                    file.createNewFile();
                }
                outputStream = new FileOutputStream(file);
                outputStream.write(list.get(j));
                //String str = list.get(j);
                //String str = new String(list.get(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将制定文件夹下的拆分文件合并为一个文件，将文件保存到指定的路径下
     *
     * @param sourceFolder 拆分文件所处的路径，其下的文件命名规则，同split拆分出来的文件名一致
     * @param targetFolder 合并之后的文件保存的路径，如果路径不存在，则创建这个目录
     */
    public void combine(File sourceFolder, File targetFolder) {


    }

    public static void main(String[] args) {
        File file = new File("javaio/WIN.mp3");
        File file1 = new File("");
        split(file, 65536, file1);
    }

}
