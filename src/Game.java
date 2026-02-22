import city.cs.engine.*;
import javax.swing.JFrame;
// Main game launcher: builds the world and view, opens the window, and starts the simulation.
public class Game {

    // The physics world that contains all bodies (player, platforms, coins, portal).
    private Level1 world;
    // The view that draws the world, background, HUD, and messages.
    private GameView view;
    // Constructor  that sets everything up and starts the game.
    public Game() {
        world = new Level1();
        view = new GameView(world, 800, 600);
        // Zoom controls how big the world looks on screen (higher = more zoomed in, lower is less zoom).
        view.setZoom(20);
        // Keyboard input is handled by Controller A/D/W for up left and right.
        view.addKeyListener(new Controller(world.getPlayer()));
// JFrame is the actual window that shows the game view.
        JFrame frame = new JFrame("Platform Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
// Makes sure the view receives keyboard input.
        view.requestFocus();
        world.start(); // // This sstarts the physics engine running.
// Runs every frame and keeps the camera centred on the player.
        world.addStepListener(new StepListener() {
            public void preStep(StepEvent e) {
                view.setCentre(world.getPlayer().getPosition());
            }
            public void postStep(StepEvent e) {}
        });
    }

    public static void main(String[] args) {
        new Game();
    }
}

