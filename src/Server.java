import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 4000;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("server start");
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("соединение установленно");
                try {
                    serverList.add(new ServerSomthing(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}