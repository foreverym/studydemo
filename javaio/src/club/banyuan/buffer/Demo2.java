package club.banyuan.buffer;

import java.io.*;

public class Demo2 {

    public static void main(String[] args) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileReader = new FileReader("");
            fileWriter = new FileWriter("");
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            String s = bufferedReader.readLine();
            while (s != null) {
                System.out.println(s);
                bufferedWriter.write(s);
                bufferedWriter.newLine();
                bufferedReader.readLine();
                bufferedWriter.flush();
                s = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
