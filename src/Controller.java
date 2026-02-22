import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// Controller handles keyboard input to control the player.
public class Controller extends KeyAdapter {
    // Reference to player so we can call movement methods.
    private final Player player;
    // Constructor connects controller to player object.
    public Controller(Player player) {
        this.player = player;
    }
    @Override // Detects key presses and moves player.
    public void keyPressed(KeyEvent e) {
        // this gives the controls so when u click A = moves left, D = moves right, W = jump.
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.moveLeft();
            case KeyEvent.VK_D -> player.moveRight();
            case KeyEvent.VK_W -> player.jump();
        }
    } // Stops movement when movement keys are released.
    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
    player.stopMoving();
        }
    }
}

