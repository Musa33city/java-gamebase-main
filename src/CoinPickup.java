import city.cs.engine.*;


// Handles collision between player and coins.
public class CoinPickup implements CollisionListener {
    // Reference to level so score can be updated.
    private final Level1 level;
    // Constructor links this listener to the level.
    public CoinPickup(Level1 level) {
        this.level = level;
    }
    @Override // Runs when player touches another body.
    public void collide(CollisionEvent e) {
        // If the object touched is a coin, collect it.
        if (e.getOtherBody() instanceof Coin coin) {
            level.collectCoin(coin);
        }
    }
}
