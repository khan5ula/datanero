package com.team13.datanero.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuestionBank {
    private HashMap<Integer, Question> questions;
    private int count;
    private List<Integer> askedQuestions;

    public QuestionBank() {
        this.count = 0;
        this.questions = new HashMap<>();
        this.askedQuestions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.put(this.count++, question);
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        int index;
    
        do {
            index = random.nextInt(this.count);
        } while (askedQuestions.contains(index));
    
        this.askedQuestions.add(index);
    
        return this.questions.get(index);
    }

    public int getCount() {
        return this.count;
    }

    public boolean areQuestionsAvailable() {
        return askedQuestions.size() < count;
    }
    
}
