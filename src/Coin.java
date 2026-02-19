import city.cs.engine.*;


public class Coin extends DynamicBody {

    private static final Shape SHAPE = new CircleShape(0.5f);
    public Coin(World world) {
        super(world, SHAPE);
        setGravityScale(0);
        addImage(new BodyImage("data/coin.gif", 1.5f));
    }
}
