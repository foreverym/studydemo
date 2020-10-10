package club.banyuan.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 先发一个byte或一行，告诉服务器端是加密还是解密，然后再发一个byte或一行，告诉服务器端的总共的文本数量。
 * 然后将文本发送给服务器端。服务器端返回加密或解密后的文本。
 *
 * 服务器先发送文本长度，然后在发送文本内容，将加密或解密后的内容返回给客户端。
 */
public class TcpServer {

    public static final int FIRST_UPPER = 65;
    public static final int FIRST_LOWER = 97;
    public static final int NUM_CHARS = 26;
    public static final int OFFSET = 3;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;
        try {
            serverSocket = new ServerSocket(5000);
            Socket socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println(s);
            String length = bufferedReader.readLine();
            System.out.println(length);
            if ("decode".equals(s)) {
                int i = bufferedReader.read();
                while (i != -1) {
                    char c = (char) i;
                    if (Character.isLetter((char) i)) {
                        c = caesarDecode((char) i);

                    }
                    System.out.print(c);
                    i = bufferedReader.read();
                }
            }
            if ("encode".equals(s)) {
                int i = bufferedReader.read();
                while (i != -1) {
                    char c = (char) i;
                    if (Character.isLetter((char) i)) {
                        c = caesarEncode((char) i);

                    }
                    System.out.print(c);
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
