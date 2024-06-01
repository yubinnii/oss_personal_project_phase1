import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player {
    private int x, y;
    private int dx;
    private ImageIcon image;

    public Player(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(imagePath);
    }

    // Method to move the player left and right
    public void move() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 750) x = 750;
    }

    // Key pressed event to move the player
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 5;
        }
    }

    // Key released event to stop the player's movement
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    // Get the player's bounds for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getIconWidth(), image.getIconHeight());
    }

    // Draw the player on the screen
    public void draw(Graphics g) {
        g.drawImage(image.getImage(), x, y, null);
    }
}
