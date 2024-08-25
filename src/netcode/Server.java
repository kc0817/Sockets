package netcode;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server {
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4999);
        // listening for new clients
        try {
            while (true) {
                System.out.println("waiting");
                Socket serverSide = serverSocket.accept();
                System.out.println("connection established");
                // handles the data that server sends to client
                ClientHandler clientHandler = new ClientHandler(serverSide);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch(IOException e) {
            System.out.println("error when listening for new clients");
            e.printStackTrace();
        } finally {
            System.out.println("inside finally block");
            closeAll();
            serverSocket.close();
            System.exit(0);
        }
        
    }
    public static void closeAll() {
        System.out.println("closing all");
        for(ClientHandler clientHandler: clientHandlers) {
            clientHandler.close();
        }
    }
    public static void outToAll(String msg) {
        System.out.println("inside outToAll | msg: " + msg);
        for(ClientHandler clientHandler: clientHandlers) {
            clientHandler.out.println("client: " + msg);
        }
    } 

}

// to send and receive messages to and from client, RELY ON CLIENT SOCKET
// to send messages to client: 