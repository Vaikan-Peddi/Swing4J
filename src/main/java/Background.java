import java.awt.Graphics2D;

public class Background {
    private Sprite sprite;

    public Background(String imagePath, int width, int height) {
        // Position is always (0,0)
        sprite = new Sprite(imagePath, 0, 0, width, height);
    }

    public void draw(Graphics2D g) {
        sprite.draw(g);
    }

    // Optional: scrolling background
    public void scrollY(double dy) {
        sprite.move(0, dy);
    }

    public void scrollX(double dx) {
        sprite.move(dx, 0);
    }
}
