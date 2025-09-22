import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class SpriteManager {
    private List<Sprite> sprites = new ArrayList<>();

    public void add(Sprite s) {
        sprites.add(s);
    }

    public void remove(Sprite s) {
        sprites.remove(s);
    }

    public List<Sprite> getAll() {
        return sprites;
    }

    public void update(double dt) {
        for (Sprite s : sprites) {
            s.update(dt);
        }
    }

    public void draw(Graphics2D g) {
        for (Sprite s : sprites) {
            s.draw(g);
        }
    }
}
