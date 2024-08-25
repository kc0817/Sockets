package netcode;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server {
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(4999);

        new Thread(new KeyboardInputListener() {
            @Override
            public void handleInput(String input) {
                checkServerCmds(input);
            }
        }).start();

        // listening for new clients
        try {
            while (true) {
                System.out.println("waiting");
                Socket serverSide = serverSocket.accept();
                System.out.println("connected");
                // handles the data that server sends to client
                ClientHandler clientHandler = new ClientHandler(serverSide, clientHandlers);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch(IOException e) {
            System.out.println("error when listening for new clients");
            e.printStackTrace();
        } finally {
            closeServerSide();
        }
        
    }
    
    private static void checkServerCmds(String input) {
        switch (input) {
            case "quit":
                closeServerSide();
                break;
        }
    }
    
    public static void closeServerSide() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("closing server side");
        System.exit(0);
    }
}
