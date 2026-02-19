import city.cs.engine.*;

public class Portal extends StaticBody {
    private boolean unlocked = false;

    public Portal(World world) {
        super(world, new BoxShape(0.8f, 2.3f));

        //this adds the portal image from the data file and gives it a size
        addImage(new BodyImage("data/portal.png", 5.6f));
    }
    public void unlock() {
        unlocked = true;
    }
    public boolean isUnlocked() {
        return unlocked;
    }
}
