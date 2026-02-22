import city.cs.engine.*;

// Coin is a collectible object that increases score when touched.
public class Coin extends DynamicBody {
    // Circular collision shape for the coin.
    private static final Shape SHAPE = new CircleShape(0.5f);
    // Constructor creates coin body and adds image.
    public Coin(World world) {
        super(world, SHAPE);
        // Gravity is disabled so the coin floats in place.
        setGravityScale(0);
        // Adds animated coin image which is a gif i found on google free gif downloads.
        addImage(new BodyImage("data/coin.gif", 1.5f));
    }
}
