import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader; // 수정6: 랭킹 정보 읽기를 위한 import 추가
import java.io.FileReader; // 수정6: 랭킹 정보 읽기를 위한 import 추가
import java.io.IOException; // 수정6: 랭킹 정보 읽기를 위한 import 추가
import java.util.ArrayList; // 수정6: 랭킹 정보 저장을 위한 import 추가
import java.util.Collections; // 수정6: 랭킹 정렬을 위한 import 추가
import java.util.Comparator; // 수정6: 랭킹 정렬을 위한 import 추가
import java.util.List; // 수정6: 랭킹 정보 저장을 위한 import 추가

public class Ranking extends JPanel {
    private JFrame parentFrame;

    public Ranking(JFrame frame) {
        this.parentFrame = frame;
        this.setLayout(null);

        // Add go start button
        JButton goStartButton = new JButton(new ImageIcon("image/gostart1.png"));
        goStartButton.setBounds(450, 250, 100, 100); 
        goStartButton.setBorderPainted(false);
        goStartButton.setContentAreaFilled(false);
        goStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMainScreen();
            }
        });
        this.add(goStartButton);

        // Load and display rankings
        List<RankingEntry> rankings = loadRankings();
        displayRankings(rankings);
    }

    // Entry to save ranking
    private static class RankingEntry {
        String name;
        int score;

        RankingEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    // Method to read ranking information from ranking.txt
    private List<RankingEntry> loadRankings() {
        List<RankingEntry> rankings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("image/ranking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    rankings.add(new RankingEntry(name, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(rankings, Comparator.comparingInt((RankingEntry entry) -> entry.score).reversed());
        return rankings;
    }

    // Method to show rankings on screen
    private void displayRankings(List<RankingEntry> rankings) {
        String[] columnNames = {"Rank", "Name", "Score"};
        String[][] data = new String[rankings.size()][3];
        for (int i = 0; i < rankings.size(); i++) {
            RankingEntry entry = rankings.get(i);
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = entry.name;
            data[i][2] = String.valueOf(entry.score);
        }

        JTable rankingTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setBounds(30, 30, 400, 300); // 수정6: 위치 조정
        this.add(scrollPane);
    }

    // Method to go to the main screen
    private void goToMainScreen() {
        MainScreen mainScreen = new MainScreen(parentFrame); 
        parentFrame.getContentPane().removeAll();
        parentFrame.add(mainScreen);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
