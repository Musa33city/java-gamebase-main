import city.cs.engine.*;
import javax.swing.JFrame;

public class Game {

    private Level1 world;
    private GameView view;

    public Game() {
        world = new Level1();
        view = new GameView(world, 800, 600);

        view.setZoom(20);
        view.addKeyListener(new Controller(world.getPlayer()));

        JFrame frame = new JFrame("Platform Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        view.requestFocus();
        world.start();

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

