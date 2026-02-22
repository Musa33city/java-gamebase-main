import city.cs.engine.*;


public class PortalContact implements CollisionListener {

    private final Level1 level;

    public PortalContact(Level1 level) {
        this.level = level;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Portal) {
            if (level.isPortalUnlocked() && !level.isGameOver()) {
                level.winLevel();
            } // above prevents the player from winning via the portal after they lost
        }
    }
}
