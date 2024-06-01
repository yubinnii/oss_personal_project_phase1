import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.*;

public class Vegetable {
    private int x, y;
    private final int dy = 10;
    private ImageIcon image;

    public Vegetable(int x, int y, ImageIcon image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void move() {
        y += dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getIconWidth(), image.getIconHeight());
    }

    public void draw(Graphics g) {
        g.drawImage(image.getImage(), x, y, null);
    }
}
