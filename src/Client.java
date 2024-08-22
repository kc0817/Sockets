import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 4999); 

        ServerConnection serverConnection = new ServerConnection(socket);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConnection).start();

        while (true) {
            System.out.println("> ");
            // receiving input from self
            String command = keyboard.readLine();

            // get line user typed
            if (command.equals("quit")) break;

            // send line to server
            out.println(command);
        }

        socket.close();
        in.close();
        out.close();
        System.exit(0);
    }
}
