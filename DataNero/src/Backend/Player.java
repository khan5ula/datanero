package Backend;

public class Player {
    private int score;
    private int lives;

    public Player(int lives) {
        this.lives = lives;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void incrementScore() {
        this.score++;
    }

    public void decrementScore() {
        if (score > 0) {
            this.score--;
        }
    }
}