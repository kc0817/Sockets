import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {

    public Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public ServerConnection(Socket s) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = in.readLine();
                System.out.println("server : " + serverResponse);
            }
        } catch (IOException e) {

        } finally {
            try {
                in.close();
            } catch (IOException e) {}
        }
    }
    
}
