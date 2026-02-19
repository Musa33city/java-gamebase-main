import city.cs.engine.*;



public class CoinPickup implements CollisionListener {

    private final Level1 level;

    public CoinPickup(Level1 level) {
        this.level = level;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin coin) {
            level.collectCoin(coin);
        }
    }
}
