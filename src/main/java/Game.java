
import java.awt.Graphics2D;

public abstract class Game {

    private boolean running = false;
    private Window window;
    private Background background;

    public abstract void init();
    public abstract void update(double dt);
    public abstract void render(Graphics2D g);

    public void start(String title, int width, int height) {
        window = new Window(title, width, height);
        new Input(window);
        init();
        running = true;
        gameLoop();
    }

    public void stop() {
        running = false;
    }

    public void setBackground(Background bg) {
        this.background = bg;
    }

    private void gameLoop() {
        long lastTime = System.nanoTime();
        final double nsPerUpdate = 1_000_000_000.0 / 60.0; // 60 FPS

        while (running) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            update(deltaTime);

            Graphics2D g = window.getGraphics();
            g.clearRect(0, 0, window.getCanvas().getWidth(), window.getCanvas().getHeight());

            if (background != null) background.draw(g);

            render(g);

            g.dispose();
            window.showFrame();

            try {
                Thread.sleep((long)(nsPerUpdate / 1_000_000.0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Window getWindow() {
        return window;
    }
}
