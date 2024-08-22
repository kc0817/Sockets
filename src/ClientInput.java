import java.io.*;

public class ClientInput extends Thread {
    private BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public void run() {
        try {
            String userInput = keyboard.readLine();
        } catch (IOException e) {}
    }
}
