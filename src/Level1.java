import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

/**
 * Level1 is the first playable level.
 * It builds the world bodies and stores simple game state (coins, timer, win/lose).
 */
public class Level1 extends World {

    // Main bodies in the level (the character and portal)
    private Player player;
    private Portal portal;

    // Coin stats
    private int coinsToCollect = 5;
    private int coinsCollected = 0;
    private final ArrayList<Coin> coins = new ArrayList<>();

    // Game state flags
    private boolean levelComplete = false;
    private boolean gameOver = false;

    // Countdown timer (seconds)
    private float timeLeft = 30.0f;

    public Level1() {
        buildLevel1();
    }

    /**
     * Builds platforms, player, coins, portal, and listeners.
     */
    private void buildLevel1() {

        // Small platforms to jump onto
        new Platform(this, -10, -8, 3, 0.5f);
        new Platform(this, 2, -4, 3, 0.5f);
        new Platform(this, 12, 0, 3, 0.5f);

        // Big floor so the player doesn't fall forever
        new Platform(this, 0, -14, 40, 4f);


        // Player spawn
        player = new Player(this);
        player.setPosition(new Vec2(-18, -12));

        // Reset jumps when landing on a platform
        player.addCollisionListener(e -> {
            if (e.getOtherBody() instanceof Platform) {
                player.resetJumps();
            }
        });

        // Coins
        spawnCoin(-10, -6.5f);
        spawnCoin(2, -2.5f);
        spawnCoin(12, 1.5f);
        spawnCoin(2, 5.5f);
        spawnCoin(-10, 9.5f);

        // Portal (locked until coins collected)
        portal = new Portal(this);
        portal.setPosition(new Vec2(18, -12));

        // Collisions e.g. picking up the coins when running into them
        player.addCollisionListener(new CoinPickup(this));
        player.addCollisionListener(new PortalContact(this));

        // StepListener runs like a simple game loop
        this.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent e) {

                // Stop updates once game is finished
                if (levelComplete || gameOver) return;

                // Player fell too far down = game over
                if (player.getPosition().y < -25) {
                    loseGame(" - Fell off the map");
                    return;
                }

                // Timer counts down using the physics timestep
                timeLeft -= 1f / 60f;

                // Time ran out = game over
                if (timeLeft <= 0) {
                    timeLeft = 0;
                    loseGame(" - Time ran out");
                }
            }

            @Override
            public void postStep(StepEvent e) { }
        });
    }

    /**
     * Spawns a coin and stores it in a list.
     */
    private void spawnCoin(float x, float y) {
        Coin c = new Coin(this);
        c.setPosition(new Vec2(x, y));
        coins.add(c);
    }

    /**
     * Called when the player touches a coin.
     * Updates counters and unlocks the portal when done.
     */
    public void collectCoin(Coin coin) {
        if (levelComplete || gameOver) return;

        coinsCollected++;
        coinsToCollect--;

        coin.destroy();

        if (coinsToCollect <= 0) {
            portal.unlock();
        }
    }

    // ---------- Getters used by HUD / Game ----------
    public Player getPlayer() {
        return player;
    }

    public int getCoinsLeft() {
        return coinsToCollect;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public boolean isPortalUnlocked() {
        return portal.isUnlocked();
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public float getTimeLeft() {
        return timeLeft;
    }

    /**
     * Ends the level as a loss and stops the simulation.
     */
    public void loseGame(String reason) {
        if (levelComplete) return; // don't lose after winning
        gameOver = true;
        this.stop();
        System.out.println("GAME OVER" + reason);
    }

    /**
     * Ends the level as a win and stops the simulation.
     */
    public void winLevel() {
        if (gameOver) return; // don't win after losing
        levelComplete = true;
        this.stop();
        System.out.println("Level Successfully Completed!!");
    }
}