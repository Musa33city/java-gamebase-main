import city.cs.engine.*;
// Portal is the exit that completes the level when unlocked.
public class Portal extends StaticBody {
    // Tracks whether the portal can be used.
    private boolean unlocked = false;
    // Creates portal body and adds its image.
    public Portal(World world) {
        super(world, new BoxShape(0.8f, 2.3f));

        //this adds the portal image from the data file and gives it a size
        addImage(new BodyImage("data/portal.png", 5.6f));
    }// Called when all coins are collected to open portal.
    public void unlock() {
        unlocked = true;
    }// Returns portal state for HUD and win condition.
    public boolean isUnlocked() {
        return unlocked;
    }
}
