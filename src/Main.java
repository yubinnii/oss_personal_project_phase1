import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Avoid Vegetables!");
        MainGame gamePanel = new MainGame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(gamePanel);
        frame.setVisible(true);

        gamePanel.startGame();
    }
}