import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class LabelManager {
    private List<Label> labels = new ArrayList<>();

    public void add(Label label) {
        labels.add(label);
    }

    public void remove(Label label) {
        labels.remove(label);
    }

    public void draw(Graphics2D g) {
        for (Label label : labels) {
            label.draw(g);
        }
    }
}
