package com.team13.datanero.backend;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScore {
    private final int maxScores = 5;
    private PriorityQueue<Score> scores;
    private static HighScore instance;

    private HighScore() {
        scores = new PriorityQueue<>(maxScores, Comparator.comparingInt(Score::getScore));
    }

    public static HighScore getInstance() {
        if (instance == null) {
            instance = new HighScore();
        }
        return instance;
    }

    public void addScore(Score score) {
        if (scores.size() < maxScores) {
            scores.offer(score);
        } else if (score.getScore() > scores.peek().getScore()) {
            scores.poll();
            scores.offer(score);
        }
    }

    /**
     * Returns a list of high score elements in descending order.
     * @return List containing 5 best high score entries.
     */
    public List<Score> getHighScore() {
        PriorityQueue<Score> tempQueue = new PriorityQueue<>(scores);
        List<Score> topScores = new ArrayList<>();
    
        while (!tempQueue.isEmpty()) {
            topScores.add(0, tempQueue.poll());
        }
    
        return topScores;
    }
}
