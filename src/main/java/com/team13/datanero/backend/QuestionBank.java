package com.team13.datanero.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;
    private int index;

    public QuestionBank() {
        this.index = 0;
        this.questions = new ArrayList<>();
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
