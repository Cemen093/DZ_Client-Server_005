import java.io.*;
import java.net.Socket;

class ServerSomthing extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run() {
        String line;
        try {
            send("you have been logged in");

            while (true) {
                line = in.readLine();
                if(line == null || line.equals("stop")) {
                    break;
                }
                send("you send my "+line);
                out.flush();
            }
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
}