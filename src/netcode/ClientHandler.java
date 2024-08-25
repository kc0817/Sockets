package netcode;
import java.io.*;
import java.net.*;

// handles input and output from server
public class ClientHandler implements Runnable {
    public BufferedReader in;
    public PrintWriter out;
    public ClientHandler(Socket serverSide) throws IOException {
        // reads input to server from client
        in = new BufferedReader(new InputStreamReader(serverSide.getInputStream()));
        // sends input from server to client
        out = new PrintWriter(serverSide.getOutputStream(), true);
    }
    public void close() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    @Override
    public void run() {
        try {
            while (true) {
                // blocking call; code will not proceed until input is recieved
                String serverMsg = in.readLine();
                System.out.println("client: " + serverMsg);
                if(serverMsg == "quit") {
                    break;
                }
                Server.outToAll(serverMsg);
                    
                
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}