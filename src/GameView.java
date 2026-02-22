import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;
import javax.swing.*;


public class GameView extends UserView{

    private final Image background;
    private final Level1 world;

    public GameView(Level1 world, int width, int height) {
        super(world, width, height);
        this.world = world;

        background = new ImageIcon("data/sandbackground.png").getImage();
    }
    @Override
    protected void paintBackground(Graphics2D g) {
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
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 260, 300);
            g.setColor(Color.BLACK);
        } else if (world.isLevelComplete()) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("LEVEL COMPLETED!", 190, 300);
            g.setColor(Color.BLACK);
        }
    }
}
