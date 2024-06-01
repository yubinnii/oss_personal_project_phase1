import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGame extends JPanel {
    private Player player;
    private List<Vegetable> vegetables;
    private Timer timer;
    private Random random;
    private ImageIcon[] Vegetable_img;
    private int score;  
    private boolean warningDisplayed;
    private int vegetableSpeed;
    private boolean showWarning;
    private Timer warningTimer;

    public MainGame() {
        player = new Player(375, 470, "image/character.png");
        vegetables = new ArrayList<>();
        random = new Random();
        Vegetable_img = new ImageIcon[] {
                new ImageIcon("image/carrot.png"),
                new ImageIcon("image/mashroom.png"),
                new ImageIcon("image/onion.png"),
                new ImageIcon("image/pa.png"),
                new ImageIcon("image/tomato.png")
        };

        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop();
            }
        });
        score = 0;
        warningDisplayed = false; // Indicates whether the warning has been displayed
        vegetableSpeed = 8; // Initial speed of the vegetables
        showWarning = false; // Indicates whether to show the warning message
    
        // Timer for blinking warning message
        warningTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWarning = !showWarning;
                repaint();
            }
        });
    }

    // Method to start the game timer
    public void startGame() {
        timer.start();
    }

    // Main game loop
    private void gameLoop() {
        player.move();
        CreateVegetable();
        moveVegetables();
        onCollision();
        updateScore();
        repaint();
    }

    // Method to create vegetables randomly
    private void CreateVegetable() {
        if (random.nextInt(100) < 8) {
            ImageIcon VegImage = Vegetable_img[random.nextInt(Vegetable_img.length)];
            vegetables.add(new Vegetable(random.nextInt(800), 0, VegImage, vegetableSpeed));
        }
    }

    // Method to move vegetables down the screen
    private void moveVegetables() {
        for (Vegetable vegetable : vegetables) {
            vegetable.move();
        }
    }

    // Method to check for collisions
    private void onCollision() {
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getBounds().intersects(player.getBounds())) {
                timer.stop();
                System.out.println("Game Over!");
            }
        }
    }

    // Method to update the score
    private void updateScore() {
        List<Vegetable> vegetablesToRemove = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getY() > 600) {
                vegetablesToRemove.add(vegetable);
                score++; 
            }
        }
        vegetables.removeAll(vegetablesToRemove);
        
        // If score is between 80 and 100, display warning
        if (score >= 80 && score < 100) { 
            if (!warningDisplayed) {
                warningDisplayed = true;
                warningTimer.start(); 
            }
        } 
        
        // If score reaches 100, increase vegetable speed and stop warning
        else if (score >= 100 && warningDisplayed) {
            warningDisplayed = false; 
            vegetableSpeed = 12; 
            warningTimer.stop(); 
            showWarning = false; 
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        player.draw(g);

        for (Vegetable vegetable : vegetables) {
            vegetable.draw(g);
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 10);
        
        if (warningDisplayed && showWarning) {
        	g.setColor(Color.RED);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 50)); 
            FontMetrics fm = g.getFontMetrics();
            int messageWidth = fm.stringWidth("Warning: Faster Vegetables!");
            int x = (getWidth() - messageWidth) / 2;
            int y = getHeight() / 2;
            g.drawString("Warning: Faster Vegetables!", x, y); 
        }
    }
}
