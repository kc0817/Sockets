import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket client;
    public BufferedReader in;
    public PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine();
                System.out.println("client : " + request);
                if(request.contains("name")) {
                    out.println("Kevin");
                } else {
                    out.println("did not find name in input");
                }
            }
        } catch (IOException e) {
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToAll(String msg) {
        for (ClientHandler client : clients) {
            client.out.println(msg);
        }
    }
}
