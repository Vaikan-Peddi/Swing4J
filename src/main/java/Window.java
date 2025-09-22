import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window {

    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;

    public Window(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // set up double buffering
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
    }

    public Graphics2D getGraphics() {
        return (Graphics2D) bufferStrategy.getDrawGraphics();
    }

    public void showFrame() {
        bufferStrategy.show();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
