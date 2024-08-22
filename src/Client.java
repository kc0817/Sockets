import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 4999); 

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


        while (true) {
            System.out.println("> ");
            // receiving input from self
            String command = keyboard.readLine();

            // get line user typed
            if (command.equals("quit")) break;
            System.out.println("client command: " + command);

            // send line to server
            out.println(command);
            
            // receiving input from server
            String serverResponse = in.readLine();
            System.out.println("server: " + serverResponse);
        }

        socket.close();
        in.close();
        out.close();
        System.exit(0);
    }
}
