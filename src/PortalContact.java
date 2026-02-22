import city.cs.engine.*;

// Detects when player reaches the portal.
public class PortalContact implements CollisionListener {
    // Reference to level to trigger win condition.
    private final Level1 level;
    // Constructor links portal collision to level.
    public PortalContact(Level1 level) {
        this.level = level;
    }
    // Checks collision between player and portal.
    @Override
    public void collide(CollisionEvent e) {
        // If portal is touched and unlocked, player wins.
        if (e.getOtherBody() instanceof Portal) {
            if (level.isPortalUnlocked() && !level.isGameOver()) {
                level.winLevel();
            } // above prevents the player from winning via the portal after they lost
        }
    }
}
