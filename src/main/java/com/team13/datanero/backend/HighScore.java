package com.team13.datanero.backend;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScore {
    private final int maxScores = 5;
    private PriorityQueue<Score> scores;
    private static HighScore instance;

    private HighScore() {
        scores = new PriorityQueue<>(maxScores, Comparator.comparingInt(Score::getScore));
    }

    /**
     * Method that returns the High Score instance. Used instead of the constructor.
     * @return HighScore instance.
     */
    public static HighScore getInstance() {
        if (instance == null) {
            instance = new HighScore();
        }
        return instance;
    }

    /**
     * Adds a new High Score entry to the score list.
     * @param score The score to be added to the list. Must be Score Object. Eg. new Score(Name, Player.getScore())
     */
    public void addScore(Score score) {
        if (scores.size() < maxScores) {
            scores.offer(score);
        } else if (score.getScore() > scores.peek().getScore()) {
            scores.poll();
            scores.offer(score);
        }
    }

    /**
     * Method that returns the lowest score stored in the High Score list.
     * <p>Returns -1 if the list is empty.
     */
    public int getLowestScore() {
        if (scores.isEmpty()) {
            return -1;
        }
        return scores.peek().getScore();
    }

    /**
     * Method that returns the count of high score records stored in the container.
     * @return Int between 0-5, the count of scores.
     */
    public int getCount() {
        return this.scores.size();
    }

    /**
     * Returns a list of high score elements in descending order.
     * 
     * @return List containing 5 best high score entries.
     */
    public ArrayList<Score> getHighScore() {
        PriorityQueue<Score> tempQueue = new PriorityQueue<>(scores);
        ArrayList<Score> topScores = new ArrayList<>();

        while (!tempQueue.isEmpty()) {
            topScores.add(0, tempQueue.poll());
        }

        return topScores;
    }
}
