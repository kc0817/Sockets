package netcode;
import java.io.*;

public abstract class KeyboardInputListener implements Runnable {
    private BufferedReader keyboard;
    public KeyboardInputListener () throws IOException {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public abstract void handleInput(String input);

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(">");
                String input = keyboard.readLine();
                handleInput(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}