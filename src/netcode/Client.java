package netcode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static Socket clientSide;
    public static void main(String[] args) throws IOException {
        clientSide = new Socket("localhost", 4999);
        System.out.println("connection established");

        new Thread(new ClientReceiver(clientSide)).start();
        PrintWriter out = new PrintWriter(clientSide.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.println("> ");
            // blocking call
            String userInput = keyboard.readLine();
            System.out.println("you typed " + userInput);
            out.println(userInput);
        }
    }
}
