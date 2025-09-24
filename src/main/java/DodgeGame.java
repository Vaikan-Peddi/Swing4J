import java.util.Random;

public class DodgeGame extends Game {
    private Sprite player;
    private SpriteManager enemies;
    private Background background;
    private Label scoreLabel;

    private int score = 0;
    private double spawnTimer = 0;
    private double spawnInterval = 1.0;
    private Random random = new Random();

    private double enemyBaseSpeed = 100;       // pixels/sec
    private double enemySpeedMultiplier = 1.0; // increases over time

    @Override
    public void init() {
        // --- Background ---
        setBackground(new Background("assets/background.jpg", 400, 600));

        // --- Player ---
        player = new Sprite("assets/player.png", 200, 500, 32, 32);

        // --- Enemy Manager ---
        enemies = new SpriteManager();

        // --- Score Label ---
        scoreLabel = new Label("Score: 0", 10, 30);
    }

    @Override
    public void update(double dt) {
        // --- Player movement ---
        if (Input.isKeyPressed(KeyCode.LEFT)) Physics.moveLeft(player);
        if (Input.isKeyPressed(KeyCode.RIGHT)) Physics.moveRight(player);
        Physics.keepInBounds(player, getWindow());

        // --- Difficulty: increase speed over time ---
        enemySpeedMultiplier += dt * 0.05; // ~5% per second

        // --- Enemy spawning ---
        spawnTimer += dt;
        if (spawnTimer > 1.0) { // every second
            spawnTimer = 0;
            double x = random.nextInt(getWindow().getCanvas().getWidth() - 32);
            Sprite enemy = new Sprite("assets/enemy.png", x, -32, 32, 32);
            enemy.setVelocity(0, enemyBaseSpeed * enemySpeedMultiplier);
            enemies.add(enemy);

            // increase score
            score++;
            scoreLabel.setText("Score: " + score);
        }

        // --- Update enemies ---
        enemies.update(dt);

        // --- Collision check (pixel-perfect) ---
        for (Sprite e : enemies.getAll()) {
            if (Physics.checkCollision(player, e)) {
                System.out.println("Game Over! Final Score: " + score);
                stop();
            }
        }

        // --- Remove off-screen enemies ---
        enemies.getAll().removeIf(e -> e.getY() > getWindow().getCanvas().getHeight());
    }

    @Override
    public void render(java.awt.Graphics2D g) {
        // --- Background is drawn automatically in Game loop ---

        // Draw player and enemies
        player.draw(g);
        enemies.draw(g);

        // Draw score
        scoreLabel.draw(g);
    }

    public static void main(String[] args) {
        new DodgeGame().start("Dodge Game", 400, 600);
    }
}

