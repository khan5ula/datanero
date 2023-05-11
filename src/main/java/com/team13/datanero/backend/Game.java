package com.team13.datanero.backend;

public class Game {
    private static Game instance;
    private Player player;
    private QuestionBank questionBank;
    private int questionsAvailable;
    private Question currentQuestion;

    private Game() {
        System.out.println("Status: Creating a new game");
        this.player = new Player();
        this.questionBank = QuestionBank.getInstance();
        this.questionBank.shuffle();
        this.questionsAvailable = this.questionBank.getCount();
        this.currentQuestion = this.questionBank.getNextQuestion();
        System.out.println("Status: Player starts with " + player.getLives() + " lives and a score of: " + player.getScore());
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.questionBank.shuffle();
            instance.questionBank.resetIndex();
            instance.currentQuestion = instance.questionBank.getNextQuestion();
        }
        return instance;
    }
    
    public static void resetInstance() {
        if (instance != null) {
            instance.questionBank.resetIndex();
            instance.questionBank.shuffle();
            instance.currentQuestion = instance.questionBank.getNextQuestion();
            instance.questionsAvailable = instance.questionBank.getCount();
            instance.player.reset();
        }
        instance = null;
    }
        

    public int getScore() {
        return this.player.getScore();
    }

    public int getLives() {
        return this.player.getLives();
    }

    public boolean submitAnswer(int answerIndex) {
        if (answerIndex != 0) {
            return false;
        }
        return true;
    }

    public void getNewQuestion() {
        this.currentQuestion = this.questionBank.getNextQuestion();
    }

    public String getCurrentQuestion() {
        return this.currentQuestion.getQuestion();
    }

    public String[] getAnswersForCurrentQuestion() {
        return this.currentQuestion.getAnswers();
    }

    public int getCountOfQuestionsAvailable() {
        return this.questionsAvailable;
    }

    public boolean areQuestionsAvailable() {
        return this.questionBank.areQuestionsAvailable();
    }

    public void incrementScore() {
        this.player.incrementScore();
    }

    public void decrementLives() {
        this.player.decrementLives();
    }
}