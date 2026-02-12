
 package game;

public class Game {

    // the current level/world
    private GameLevel1 level;

    // the view window that displays the world
    private GameView view;

    // keyboard controller (clicking space bar to flap)
    private Controller controller;

    // game stats
    private int lives = 3;
    private int score = 0;

    public Game() {
        // starting the game at level 1
        goToLevel(new Level1(this));
    }

    /*
    switching to a new level (world), the old world stops, the new world
    is created and starts.
     */
    public void goToLevel(GameLevel newLevel) {
        if (level != null) {
            level.stop(); // this stops physics stepping in old world
        }

        level = newLevel;
        level.populate(); // this builds bodies for the level

        // Creating the view one time then swapping the world inside it
        if (view == null) {
            view = new GameView(level, this, 800, 600);
            view.setZoom(20); // This adjusts the scene if you want it bigger or smaller
        } else {
            view.setWorld(level);
        }
        // The controller needs to control the bird
        if (controller == null) {
            controller = new Controller(level.getBird());
            view.addKeyListener(controller);
    } else {
            controller.setBird(level.getBird());
        }
        level.start();

}
/** Adds to the score and sees if we should progress to the next level */

public void addScore(int amount) {
    score += amount;

    // progression rules:
    // Level 1 to 2 when the score is >=5
    // Level 2 to 3 when the score is >=12
    if (level instanceof Level1 && score >= 5) {
        goToLevel(new Level2(this));
    } else if level instanceof Level2 && score >= 12){
        goToLevel1(new Level3(this));
    }
}
/** Losing a life, if you have no lives left then the game restarts. */
public void loseLife() {
    lives--;
    if (lives == 0) {
// Game over, everything restarts
    lives = 3;
    score = 0;
    goToLevel(new Level1(this));
    } else {
        // Resets the bird so that the player can continue
        level.resetBird();
    }
}
// Getters used by the HUD (GameView)
public int getLives() { return lives; }
public int getScore() { return score; }
public int getLevelNumber() { return level.getLevelNumber(); }

public static void main(String[] args) {
    new Game();
    }
}


