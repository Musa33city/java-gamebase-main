import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;
import javax.swing.*;

// GameView handles all drawing: background, HUD, timer, and win/lose messages.
public class GameView extends UserView{
    // Background image displayed behind the physics world.
    private final Image background;
    private final Level1 world; // Reference to the world so we can read game stats like coins and timer.
    // Constructor sets the world reference and loads the background image.
    public GameView(Level1 world, int width, int height) {
        super(world, width, height);
        this.world = world;

        background = new ImageIcon("data/sandbackground.png").getImage();
    }
    @Override
    protected void paintBackground(Graphics2D g) { // Draws the background image each frame.
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
    @Override
    public void paintForeground(Graphics2D g) {

        // HUD font
        g.setFont(new Font("Arial", Font.BOLD, 18));

        // Timer (top right)
        g.drawString("Time: " + (int) world.getTimeLeft(), 680, 30);

        // Coin stats
        g.drawString("Coins collected: " + world.getCoinsCollected(), 20, 30);
        g.drawString("Coins left: " + world.getCoinsLeft(), 20, 55);

        // Portal status
        if (world.isPortalUnlocked()) {
            g.drawString("Portal: OPEN", 20, 80);
        } else {
            g.drawString("Portal: LOCKED", 20, 80);
        }

        // WIN / LOSE messages (must be OUTSIDE the portal locked/unlocked check)
        if (world.isGameOver()) {
            g.setColor(Color.RED);  // Displays a large red GAME OVER message if the player loses.
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 260, 300);
            g.setColor(Color.BLACK);
        } else if (world.isLevelComplete()) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("LEVEL COMPLETED!", 190, 300); // Displays a large green message when the level is completed.
            g.setColor(Color.BLACK); // resets colour to black for future text if needed
        }
    }
}
