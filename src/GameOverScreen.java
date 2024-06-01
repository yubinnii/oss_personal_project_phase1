import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter; 
import java.io.IOException; 

public class GameOverScreen extends JPanel {
    private JFrame parentFrame;
    private int score;

    public GameOverScreen(JFrame frame, int score) {
        this.parentFrame = frame;
        this.score = score;
        this.setLayout(null);

        // Add vomit image
        JLabel vomitImage = new JLabel(new ImageIcon("image/vomitdog.png"));
        vomitImage.setBounds(100, 30, vomitImage.getPreferredSize().width, vomitImage.getPreferredSize().height);
        this.add(vomitImage);

        // Add score label
        JLabel scoreLabel = new JLabel("score: "+ score , SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(350, 30, 200, 50); 
        this.add(scoreLabel);

        // Add replay button
        JButton replayButton = new JButton(new ImageIcon("image/replay1.png"));
        replayButton.setBounds(430, 70, 100, 100); 
        replayButton.setBorderPainted(false);
        replayButton.setContentAreaFilled(false);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        this.add(replayButton);

        // Add ranking button
        JButton rankingButton = new JButton(new ImageIcon("image/ranking1.png"));
        rankingButton.setBounds(430, 150, 100, 100); 
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gotoRankingInput();
            }
        });
        this.add(rankingButton);

        // Add go start button
        JButton goStartButton = new JButton(new ImageIcon("image/gostart1.png"));
        goStartButton.setBounds(430, 230, 100, 100); 
        goStartButton.setBorderPainted(false);
        goStartButton.setContentAreaFilled(false);
        goStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMainScreen();
            }
        });
        this.add(goStartButton);
    }

    // Method to start the game again
    private void startGame() {
        MainGame gamePanel = new MainGame(parentFrame); 
        parentFrame.getContentPane().removeAll();
        parentFrame.add(gamePanel);
        parentFrame.revalidate();
        parentFrame.repaint();
        gamePanel.requestFocusInWindow();
        gamePanel.startGame();
    }

    // Method to go to the main screen
    private void goToMainScreen() {
        MainScreen mainScreen = new MainScreen(parentFrame); 
        parentFrame.getContentPane().removeAll();
        parentFrame.add(mainScreen);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
    
    // Method to go to Ranking Input page
    private void gotoRankingInput() {
        RankingInput rankingInputPage = new RankingInput(parentFrame, score); 
        parentFrame.getContentPane().removeAll();
        parentFrame.add(rankingInputPage);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
