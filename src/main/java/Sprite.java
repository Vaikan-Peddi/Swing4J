import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
    private BufferedImage image;
    private double x, y;   // position
    private int width, height;
    private double vx, vy; // velocity (pixels per second)

    public Sprite(String imagePath, double x, double y, int width, int height) {
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException("Could not load sprite image: " + imagePath);
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // --- Position ---
    public void setPosition(double x, double y) {
        this.x = x; this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // --- Movement ---
    public void move(double dx, double dy) {
        // per frame (no dt)
        this.x += dx;
        this.y += dy;
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void update(double dt) {
        // if velocity is set, use dt for frame-rate independence
        this.x += vx * dt;
        this.y += vy * dt;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getVelocityX() {
        return vx;
    }

    public double getVelocityY() {
        return vy;
    }

    public BufferedImage getImage() {
        return image;
    }

    // --- Drawing ---
    public void draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
