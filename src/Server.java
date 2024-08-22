import java.io.*;
import java.net.*;
import java.util.ArrayList;
public class Server {

    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(4999);
        try {
            while (true) {
                System.out.println("waiting");
                Socket client = listener.accept();
                System.out.println("connected");
                ClientHandler clientThread = new ClientHandler(client, clients);
                clients.add(clientThread);
                new Thread(clientThread).start();
            }
        } catch (IOException e) {
        }finally {
            listener.close();
            System.exit(0);
        }
        
    }
}