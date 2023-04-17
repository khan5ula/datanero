package Backend;

public class Player {
    private int score;
    private int lives;

    public Player(int initialLives) {
        this.score = 0;
        this.lives = initialLives;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        score--;
    }

    public void decrementLives() {
        lives--;
    }

    public boolean isAlive() {
        return lives > 0;
    }
}
