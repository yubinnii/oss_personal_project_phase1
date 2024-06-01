import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.*;

public class Vegetable {
    private int x, y;
    private int dy;
    private ImageIcon image;

    public Vegetable(int x, int y, ImageIcon image, int dy) {
        this.x = x;
        this.y = y;
        this.dy = dy;
        this.image = image;
    }

    // Method to move the vegetable down the screen
    public void move() {
        y += dy;
    }
    
    public int getY() {
        return y;
    }

    // Get the vegetable's bounds for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getIconWidth(), image.getIconHeight());
    }

    // Draw the vegetable on the screen
    public void draw(Graphics g) {
        g.drawImage(image.getImage(), x, y, null);
    }
}
