package netcode;
import java.io.*;
import java.net.*;

public class Client {
    private static Socket clientSide;
    private static PrintWriter out;
    public static void main(String[] args) throws IOException {
        clientSide = new Socket("10.0.0.3", 4999);
        System.out.println("connection established");

        out = new PrintWriter(clientSide.getOutputStream(), true);
        new Thread(new KeyboardInputListener() {
            @Override
            public void handleInput(String input) {
                out.println(input);
            }
        }).start();

        new Thread(new ClientReceiver(clientSide)).start();
    }

    public static void closeClientSide() {
        System.out.println("closing client side");
        try {
            clientSide.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
