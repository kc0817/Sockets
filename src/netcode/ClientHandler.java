package netcode;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

// handles input and output from server
public class ClientHandler implements Runnable {
    public BufferedReader in;
    public PrintWriter out;
    private ArrayList<ClientHandler> clientHandlers;

    enum States {
        USERNAME, CHAT
    }
    public States state = States.USERNAME;
    public String username = "Client";

    public ClientHandler(Socket serverSide, ArrayList<ClientHandler> clientHandlers) throws IOException {
        in = new BufferedReader(new InputStreamReader(serverSide.getInputStream()));
        out = new PrintWriter(serverSide.getOutputStream(), true);

        this.clientHandlers = clientHandlers;
        
        out.println("Enter a usename");
    }
    public void checkStandardCmds(String msg) {

    }
    public void close() {
        Client.closeClientSide();
        Server.closeServerSide();
        System.exit(0);
    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = in.readLine();
                System.out.println("client msg: " + msg);

                // checking standard server commands
                if(msg.equals("quit")) {
                    break;
                }  

                // handling normal function
                switch (state) {
                    case USERNAME:
                        if (tryToJoin(msg)) {
                            System.out.println("new client " + username + " joined");
                        }
                        break;
                    case CHAT:
                        outToAll(username + " : " + msg, this);
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    public void outToAll(String msg, ClientHandler originClientHandler) {
        for (ClientHandler clientHandler: clientHandlers) {
            if(!clientHandler.equals(originClientHandler))
            clientHandler.out.println(msg);
        }
    } 
    
    private boolean tryToJoin(String msg) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.equals(this) && clientHandler.username.equals(msg)) {
                // means username is already taken
                out.println("server : username already taken");
                return false;
            }
        }
        username = msg;
        out.println("username " + username + " set");
        outToAll("new user: " + username + " joined", this);
        state = States.CHAT;
        return true;
    }
}