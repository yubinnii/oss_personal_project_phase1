import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel {
    private JFrame parentFrame;

    public MainScreen(JFrame frame) {
        this.parentFrame = frame;
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel(new ImageIcon("image/title.png"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

        JLabel description = new JLabel(new ImageIcon("image/desc.png"));
        description.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(description, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton startGameButton = new JButton(new ImageIcon("image/startGame.png"));
        startGameButton.setBorderPainted(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	startGame();
            }
        });
        buttonPanel.add(startGameButton);

        JButton viewRankingButton = new JButton(new ImageIcon("image/ranking2.png"));
        viewRankingButton.setBorderPainted(false);
        viewRankingButton.setContentAreaFilled(false);
        viewRankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //랭킹
            }
        });
        buttonPanel.add(viewRankingButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
        
    }
    private void startGame() {
        MainGame gamePanel = new MainGame();
        parentFrame.getContentPane().removeAll();
        parentFrame.add(gamePanel);
        parentFrame.revalidate();
        parentFrame.repaint();
        gamePanel.requestFocusInWindow();
        gamePanel.startGame();
    }
}
