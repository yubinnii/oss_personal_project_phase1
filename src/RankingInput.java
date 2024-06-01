import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter; // 수정6: 랭킹 정보 저장을 위한 import 추가
import java.io.FileWriter; // 수정6: 랭킹 정보 저장을 위한 import 추가
import java.io.IOException; // 수정6: 랭킹 정보 저장을 위한 import 추가

public class RankingInput extends JPanel {
    private JFrame parentFrame;
    private int score;

    public RankingInput(JFrame frame, int score) {
        this.parentFrame = frame;
        this.score = score;
        this.setLayout(null);

        // Add name label
        JLabel nameLabel = new JLabel("Name: ", SwingConstants.LEFT);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(120, 130, 100, 30); 
        this.add(nameLabel);

        // Add name text field
        JTextField nameField = new JTextField();
        nameField.setBounds(220, 130, 200, 30); 
        this.add(nameField);

        // Add score label
        JLabel scoreLabel = new JLabel("score : " + score, SwingConstants.LEFT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(120, 180, 200, 30); 
        this.add(scoreLabel);

        // Add submit button
        JButton saveButton = new JButton(new ImageIcon("image/ranking1.png"));
        saveButton.setBounds(300, 180, 100, 75); 
        saveButton.setBorderPainted(false);
        saveButton.setContentAreaFilled(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    saveRanking(name, score);
                    gotoRanking();
                }
            }
        });
        this.add(saveButton);
    }

    // Method to save ranking
    private void saveRanking(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("image/ranking.txt", true))) {
            writer.write(name + " " + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to go to ranking page
    private void gotoRanking() {
        Ranking rankingPage = new Ranking(parentFrame); 
        parentFrame.getContentPane().removeAll();
        parentFrame.add(rankingPage);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
