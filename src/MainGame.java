import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
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
    }

    public void startGame() {
        timer.start();
    }

    private void gameLoop() {
        player.move();
        CreateVegetable();
        moveVegetables();
        onCollision();
        repaint();
    }

    private void CreateVegetable() {
        if (random.nextInt(100) < 8) {
            ImageIcon VegImage = Vegetable_img[random.nextInt(Vegetable_img.length)];
            vegetables.add(new Vegetable(random.nextInt(800), 0, VegImage));
        }
    }

    private void moveVegetables() {
        for (Vegetable vegetable : vegetables) {
            vegetable.move();
        }
    }

    private void onCollision() {
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getBounds().intersects(player.getBounds())) {
                timer.stop();
                System.out.println("Game Over!");
            }
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
    }
}
