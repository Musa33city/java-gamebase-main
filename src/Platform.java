import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Platform extends StaticBody {

    public Platform(World world, float x, float y, float halfW, float halfH) {
        super(world, new BoxShape(halfW, halfH));
        setPosition(new Vec2(x, y));
        addImage(new BodyImage("data/sandfloor.png", halfH * 2));
    }
}