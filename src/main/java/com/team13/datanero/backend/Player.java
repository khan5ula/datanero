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
        System.out.println("Status: Incrementing player score");
        this.score++;
        System.out.println("Status: Player score is now: " + getScore());
    }

    public void decrementLives() {
        System.out.println("Status: Decrementing player lives");
        if (this.lives > 0) {
            this.lives--;
        }
        System.out.println("Status: Player has now " + getLives() + " lives");
    }

    public void reset() {
        System.out.println("Status: Resetting player status");
        this.lives = 3;
        this.score = 0;
        System.out.println("Status: Player now has " + getLives() + " lives and score of: " + getScore());
    }
}