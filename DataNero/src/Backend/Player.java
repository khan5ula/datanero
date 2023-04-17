package Backend;

public class Player {
    private int score;
    private int lives;

    public Player(int initialLives) {
        this.score = 0;
        this.lives = initialLives;
    }

    public int getScore() {
        return this.score;
    }

    public int getLives() {
        return this.lives;
    }

    public void incrementScore() {
        this.score++;
    }

    public void decrementScore() {
        this.score--;
    }

    public void decrementLives() {
        this.lives--;
    }

    public boolean isAlive() {
        return this.lives > 0;
    }
}
