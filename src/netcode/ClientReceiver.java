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
            }
        } catch (IOException e) {
            System.out.println("inside catch statement");
        } finally {
            
            System.out.println("inside finally for client");
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } 
            System.exit(0);
        }
    }
    
}
