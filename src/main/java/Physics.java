public class Physics {
    private static double speed = 5; // default per-frame speed

    // --- Basic Movement ---
    public static void moveUp(Sprite s) {
        s.move(0, -speed);
    }

    public static void moveDown(Sprite s) {
        s.move(0, speed);
    }

    public static void moveLeft(Sprite s) {
        s.move(-speed, 0);
    }

    public static void moveRight(Sprite s) {
        s.move(speed, 0);
    }

    public static void setSpeed(double newSpeed) {
        speed = newSpeed;
    }

    // --- Collision Detection ---
    public static boolean checkCollision(Sprite a, Sprite b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

    // --- Pixel-Perfect Collision Detection ---
    public static boolean checkPixelCollision(Sprite a, Sprite b) {
        int ax = (int)a.getX();
        int ay = (int)a.getY();
        int bx = (int)b.getX();
        int by = (int)b.getY();

        int width = Math.min(ax + a.getWidth(), bx + b.getWidth()) - Math.max(ax, bx);
        int height = Math.min(ay + a.getHeight(), by + b.getHeight()) - Math.max(ay, by);

        if (width <= 0 || height <= 0) return false; // no overlap

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int axPixel = Math.max(0, bx - ax) + i;
                int ayPixel = Math.max(0, by - ay) + j;

                int bxPixel = Math.max(0, ax - bx) + i;
                int byPixel = Math.max(0, ay - by) + j;

                if (((a.getImage().getRGB(axPixel, ayPixel) >> 24) & 0xff) != 0 &&
                        ((b.getImage().getRGB(bxPixel, byPixel) >> 24) & 0xff) != 0) {
                    return true; // opaque pixel overlap
                }
            }
        }
        return false;
    }


    public static boolean pointInside(Sprite s, int x, int y) {
        return x >= s.getX() && x <= s.getX() + s.getWidth() &&
                y >= s.getY() && y <= s.getY() + s.getHeight();
    }

    // --- Keep inside window ---
    public static void keepInBounds(Sprite s, Window w) {
        if (s.getX() < 0) s.setPosition(0, s.getY());
        if (s.getY() < 0) s.setPosition(s.getX(), 0);

        int maxX = w.getCanvas().getWidth() - s.getWidth();
        int maxY = w.getCanvas().getHeight() - s.getHeight();

        if (s.getX() > maxX) s.setPosition(maxX, s.getY());
        if (s.getY() > maxY) s.setPosition(s.getX(), maxY);
    }

    // --- Simple gravity (optional) ---
    public static void applyGravity(Sprite s, double gravity, double dt) {
        s.setVelocity(s.getVelocityX(), s.getVelocityY() + gravity * dt);
    }

    public static void jump(Sprite s, double jumpStrength) {
        s.setVelocity(s.getVelocityX(), -jumpStrength);
    }
}
