import city.cs.engine.*;
import org.jbox2d.common.Vec2;

// Platform represents solid ground or surfaces the player can stand on.
public class Platform extends StaticBody {
    // Constructor creates a rectangular platform and places it in the world.
    public Platform(World world, float x, float y, float halfW, float halfH) {
        // BoxShape defines the physical collision size of the platform.
        super(world, new BoxShape(halfW, halfH));
        // Sets the platform position in the world using x and y coordinates.
        setPosition(new Vec2(x, y));
        // Adds the png image of the platform i chose. I found the image on google pictures.
        addImage(new BodyImage("data/sandfloor.png", halfH * 2));
    }
}