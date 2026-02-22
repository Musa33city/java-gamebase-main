import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends Walker {

    private static final Shape SHAPE = new BoxShape(0.7f, 1.2f);
private int jumpsUsed = 0;
private static final int MAX_JUMPS = 2;

    private final BodyImage rightImg = new BodyImage("data/character.png", 2.5f);
    private final BodyImage leftImg = new BodyImage("data/characterLeft.png", 2.5f);

    public Player(World world) {
        super(world, SHAPE);

    addImage(rightImg);
}
    public void moveLeft() {
      removeAllImages();
      addImage(leftImg);
      startWalking(-6);
    }
    public void moveRight() {
        removeAllImages();
        addImage(rightImg);
        startWalking(6);

    }
    public void stopMoving() {
        stopWalking();
    } public void resetJumps() {
        jumpsUsed = 0;
    }
    public void jump() {
        if (jumpsUsed < MAX_JUMPS) {
            Vec2 v = getLinearVelocity();
            setLinearVelocity(new Vec2(v.x, 10));
            jumpsUsed++;
        }
    }
}