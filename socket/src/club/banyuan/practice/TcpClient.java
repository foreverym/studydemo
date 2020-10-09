package club.banyuan.practice;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {

    public static final int FIRST_UPPER = 65;
    public static final int FIRST_LOWER = 97;
    public static final int NUM_CHARS = 26;
    public static final int OFFSET = 3;

    public static void main(String[] args) {
        FileReader fileReader = null;
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            BufferedReader bufferedReader = null;
            BufferedWriter clientBufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clientBufferedWriter.write(s);
            long length;
            File file = null;
            if ("decode".equals(s)) {
                file = new File("socket/aliceEncode.code");
                new BufferedReader(new FileReader(file));
                length = file.length();
                clientBufferedWriter.write((int)length);
                int i = bufferedReader.read();
                while (i != -1) {
                    char c = (char) i;
                    if (Character.isLetter((char) i)) {
                        c = caesarDecode((char) i);
                    }
                    clientBufferedWriter.write(c);
                    i = bufferedReader.read();
                }

            }
            if ("encode".equals(s)) {
                file = new File("socket/aliceDecode.code");
                new BufferedReader(new FileReader(file));
                length = file.length();
                clientBufferedWriter.write((int)length);
                clientBufferedWriter.write((int)length);
                int i = bufferedReader.read();
                while (i != -1) {
                    char c = (char) i;
                    if (Character.isLetter((char) i)) {
                        c = caesarEncode((char) i);
                    }
                    clientBufferedWriter.write(c);
                    i = bufferedReader.read();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static char caesarDecode(char ch) {
        if (Character.isUpperCase(ch)) {
            return (char) ((ch - Encode.FIRST_UPPER + Encode.NUM_CHARS - Encode.OFFSET) % Encode.NUM_CHARS
                    + Encode.FIRST_UPPER);
        } else if (Character.isLowerCase(ch)) {
            return (char) ((ch - Encode.FIRST_LOWER + Encode.NUM_CHARS - Encode.OFFSET) % Encode.NUM_CHARS
                    + Encode.FIRST_LOWER);
        } else {
            return ch;
        }
    }

    public static char caesarEncode(char ch) {

        if (Character.isUpperCase(ch)) {
            return (char) ((ch - FIRST_UPPER + OFFSET) % NUM_CHARS + FIRST_UPPER);
        } else if (Character.isLowerCase(ch)) {
            return (char) ((ch - FIRST_LOWER + OFFSET) % NUM_CHARS + FIRST_LOWER);
        } else {
            return ch;
        }
    }

}
