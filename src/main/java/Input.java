import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private static final Set<Integer> keysPressed = new HashSet<>();
    private static final Set<Integer> mousePressed = new HashSet<>();
    private static int mouseX, mouseY;

    public Input(Window window) {
        window.getCanvas().addKeyListener(this);
        window.getCanvas().addMouseListener(this);
        window.getCanvas().addMouseMotionListener(this);
        window.getCanvas().setFocusable(true);
        window.getCanvas().requestFocus();
    }

    // --- Keyboard ---
    public static boolean isKeyPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // --- Mouse ---
    public static boolean isMousePressed(int button) {
        return mousePressed.contains(button);
    }

    public static int getMouseX() { return mouseX; }
    public static int getMouseY() { return mouseY; }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed.add(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed.remove(e.getButton());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
