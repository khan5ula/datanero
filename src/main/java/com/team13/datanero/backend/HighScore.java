package com.team13.datanero.backend;

import java.util.PriorityQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScore {
    private final int maxScores = 5;
    private PriorityQueue<Score> scores;
    private static HighScore instance;
    private DataBase scoreDatabase;

    private HighScore() {
        scores = new PriorityQueue<>(maxScores, Comparator.comparingInt(Score::getScore));
        this.scoreDatabase = DataBase.getInstance();
    }

    /**
     * Method that returns the High Score instance. Used instead of the constructor.
     * 
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
     * 
     * @param score The score to be added to the list. Must be Score Object. Eg. new
     *              Score(Name, Player.getScore())
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
     * <p>
     * Returns -1 if the list is empty.
     */
    public int getLowestScore() {
        if (scores.isEmpty()) {
            return -1;
        }
        return scores.peek().getScore();
    }

    /**
     * Method that returns the count of high score records stored in the container.
     * 
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

    /**
     * Method that gets the High Scores from the database.
     * <p>The retrieved High Score records are stored to the class variable container.
     */
    public void getScoresFromDatabase() {
        try {
            System.out.println("Status: HighScore instance is fetching High Score records from database");
            this.scores.clear();
            ArrayList<Score> scoresArrayList = this.scoreDatabase.getScores();
            for (int i = 0; i < scoresArrayList.size(); i++) {
                addScore(scoresArrayList.get(i));
            }
        } catch (SQLException e) {
            System.out.println("Error: SQLException occured with HighScore instance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method that stores the High Scores to the database.
     * Method also removes the previous records from the database before it sets the new ones there.
     */
    public void setScoresToDatabase() {
        ArrayList<Score> scoresArrayList = getHighScore();
        DataBase dbInstance = DataBase.getInstance();
        try {
            dbInstance.clearScoresTable();
            for (Score score : scoresArrayList) {
                dbInstance.setScore(score);
            }
        } catch (SQLException e) {
            System.out.println("Error: SQLException occured while setting scores to Database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
