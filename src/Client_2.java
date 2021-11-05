import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client_2 {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
//        Реализовать клиент-серверное приложение с использованием ООП.
//        Один сервер - множество клиентов.
//        Клиент при подключении получает сообщение от сервера о том, что он прошел авторизацию.
        try {
            clientSocket = new Socket("localhost", 4000);
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private static class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;

            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("stop")) {
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {

            }
        }
    }

    public static class WriteMsg extends Thread {

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line;
                try {
                    line = scanner.nextLine();
                    if (line.equals("stop")) {
                        out.write("stop" + "\n");
                        System.exit(0);
                        break;
                    } else {
                        out.write(line + "\n");
                    }
                    out.flush(); // чистим
                } catch (IOException e) {

                }

            }
        }
    }
}