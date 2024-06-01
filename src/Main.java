import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    	// Create the main JFrame window
        JFrame frame = new JFrame("Avoid Vegetables!");
        MainScreen mainScreen = new MainScreen(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(mainScreen);
        frame.setVisible(true);
    }
}
