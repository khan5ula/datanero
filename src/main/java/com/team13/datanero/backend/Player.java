package com.team13.datanero.backend;

public class Player {
    private int score;
    private int lives;

    public Player() {
        this.lives = 3;
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

    public void reset() {
        this.lives = 3;
        this.score = 0;
    }
}