import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private boolean gameOver; 
    private JFrame parentFrame;
    
    public MainGame(JFrame frame) {
    	this.parentFrame = frame;
        player = new Player(275, 270, "image/character.png");
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
        vegetableSpeed = 6; // Initial speed of the vegetables
        showWarning = false; // Indicates whether to show the warning message
        gameOver = false; // Indicates whether the game is over
    
        // Timer for blinking warning message
        warningTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWarning = !showWarning;
                repaint();
            }
        });
        
        
        // Add Mouse Click Listener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameOver) {
                    showGameOverScreen();
                }
            }
        });
    }

    // Method to start the game timer
    public void startGame() {
        timer.start();
    }

    // Main game loop
    private void gameLoop() {
    	if (!gameOver) { 
            player.move();
            CreateVegetable();
            moveVegetables();
            onCollision();
            updateScore();
            repaint();
        }
    }

    // Method to create vegetables randomly
    private void CreateVegetable() {
        if (random.nextInt(100) < 6) {
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
            	gameOver = true;
                timer.stop();
                warningTimer.stop();
            }
        }
    }

    // Method to update the score
    private void updateScore() {
        List<Vegetable> vegetablesToRemove = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getY() > 400) {
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
            vegetableSpeed = 10; 
            warningTimer.stop(); 
            showWarning = false; 
        }
    }
    
    // Method to show Gameover screen
    private void showGameOverScreen() {
    	parentFrame.getContentPane().removeAll();
        GameOverScreen gameOverScreen = new GameOverScreen(parentFrame, score); // 수정4: GameOverScreen 생성
        parentFrame.add(gameOverScreen);
        parentFrame.revalidate();
        parentFrame.repaint();
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
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30)); 
            FontMetrics fm = g.getFontMetrics();
            int messageWidth = fm.stringWidth("Warning: Faster Vegetables!");
            int x = (getWidth() - messageWidth) / 2;
            int y = getHeight() / 2;
            g.drawString("Warning: Faster Vegetables!", x, y); 
        }
        
        if (gameOver){ 
        	g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics fm = g.getFontMetrics();
            String gameOverText = "Game Over";
            String clickText = "Click to continue";
            int gameOverWidth = fm.stringWidth(gameOverText);
            int clickWidth = fm.stringWidth(clickText);
            int xGameOver = (getWidth() - gameOverWidth) / 2;
            int yGameOver = getHeight() / 2 - 25;
            int xClick = (getWidth() - clickWidth) / 2;
            int yClick = getHeight() / 2 + 25;
            g.drawString(gameOverText, xGameOver, yGameOver);
            g.drawString(clickText, xClick, yClick);
        } 
    }
}
