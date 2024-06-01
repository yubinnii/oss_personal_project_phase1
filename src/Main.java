import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Avoid Vegetables!");
        MainScreen mainScreen = new MainScreen(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(mainScreen);
        frame.setVisible(true);
    }
}
