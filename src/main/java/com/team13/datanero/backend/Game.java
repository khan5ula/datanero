package com.team13.datanero.backend;

public class Game {
    private static Game instance;
    private Player player;
    private QuestionBank questionBank;
    private int questionsAvailable;
    private Question currentQuestion;

    private Game(int lives, QuestionBank questionBank) {
        this.player = new Player(lives);
        this.questionBank = questionBank;
        this.questionBank.shuffle();
        this.questionsAvailable = this.questionBank.getCount();
        this.currentQuestion = this.questionBank.getNextQuestion();
    }

    public static Game getInstance(int lives, QuestionBank questionBank) {
        if (instance == null) {
            instance = new Game(lives, questionBank);
            instance.questionBank.shuffle();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
        instance.questionBank.resetIndex();
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
}