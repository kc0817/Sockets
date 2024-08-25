package netcode;
import java.io.*;
import java.net.Socket;

public class ClientReceiver implements Runnable {
    private BufferedReader in;

    public ClientReceiver(Socket clientSide) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSide.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverMsg = in.readLine();
                
                if(serverMsg == null) {
                    continue;
                }

                System.out.println(serverMsg);
                System.out.println(">");
            }
        } catch (IOException e) {
        } finally {
            Client.closeClientSide();
        }
    }
    
}
