package com.team13.datanero.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private static QuestionBank instance;
    private List<Question> questions;
    private int index;

    private QuestionBank() {
        this.index = 0;
        this.questions = new ArrayList<>();
    }

    public static QuestionBank getInstance() {
        if (instance == null) {
            instance = new QuestionBank();
        }
        return instance;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void shuffle() {
        Collections.shuffle(questions);
    }

    public void resetIndex() {
        this.index = 0;
    }

    public Question getQuestionByIndex(int index) {
        return this.questions.get(index);
    }

    public int getCount() {
        return this.questions.size();
    }

    public boolean areQuestionsAvailable() {
        return index < getCount();
    }
    
    public Question getNextQuestion() {
        if (!areQuestionsAvailable()) {
            return null;
        }

        Question nextQuestion = getQuestionByIndex(index);
        index++;
        return nextQuestion;
    }
}
