import java.awt.Color;
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

    public void move() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 750) x = 750;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 5;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getIconWidth(), image.getIconHeight());
    }

    public void draw(Graphics g) {
    	g.drawImage(image.getImage(), x, y, null);
    }
}