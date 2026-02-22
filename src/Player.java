import city.cs.engine.*;
import org.jbox2d.common.Vec2;
// Player represents the controllable character and handles movement and jumping.
public class Player extends Walker {
    // Defines the collision shape (hitbox) of the player.
    private static final Shape SHAPE = new BoxShape(0.7f, 1.2f);
private int jumpsUsed = 0; // Tracks how many jumps have been used since last landing.
private static final int MAX_JUMPS = 2; // limits jumps to only 2 (double jumps)
    // Image when player is facing right.
    private final BodyImage rightImg = new BodyImage("data/character.png", 2.5f);
    // Image when player is facing left.
    private final BodyImage leftImg = new BodyImage("data/characterLeft.png", 2.5f);
    // Constructor that sets up the player body and starting image.
    public Player(World world) {
        super(world, SHAPE);

    addImage(rightImg);
}// Moves the player left and switches image direction to the direction the character is going
    public void moveLeft() {
      removeAllImages();
      addImage(leftImg);
      startWalking(-6);
    }// Moves the player right and switches image direction to the right.
    public void moveRight() {
        removeAllImages();
        addImage(rightImg);
        startWalking(6);

    }// Stops horizontal movement when keys are released.
    public void stopMoving() {
        stopWalking();
    } public void resetJumps() { // Resets jump counter when player lands on a platform.
        jumpsUsed = 0;
    } // Applies upward velocity (jumps) if jump limit has not been reached.
    public void jump() {
        if (jumpsUsed < MAX_JUMPS) {
            Vec2 v = getLinearVelocity();
            setLinearVelocity(new Vec2(v.x, 10));
            jumpsUsed++;
        }
    }
}