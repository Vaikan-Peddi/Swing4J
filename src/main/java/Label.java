import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Label {
    private String text;
    private int x, y;
    private Color color;
    private Font font;

    public Label(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = Color.WHITE; // default color
        this.font = new Font("Arial", Font.BOLD, 20); // default font
    }

    // --- Setters ---
    public void setText(String text) { this.text = text; }
    public void setColor(Color color) { this.color = color; }
    public void setFont(Font font) { this.font = font; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    // --- Draw ---
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
