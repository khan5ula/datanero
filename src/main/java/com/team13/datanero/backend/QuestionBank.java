package com.team13.datanero.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuestionBank {
    private HashMap<Integer, Question> questions;
    private int count;
    private List<Integer> askedQuestions;
    private boolean questionsAvailable;

    public QuestionBank() {
        this.count = 0;
        this.questions = new HashMap<>();
        this.askedQuestions = new ArrayList<>();
        questionsAvailable = false;
    }

    public void addQuestion(Question question) {
        this.questions.put(this.count++, question);
        this.count++;
        questionsAvailable = true;
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        int index;

        do {
            index = random.nextInt(this.count) + 1;
        } while (askedQuestions.contains(index));

        this.askedQuestions.add(index);

        if (askedQuestions.size() == this.count) {
            this.questionsAvailable = false;
        }

        return this.questions.get(index);
    }

    public int getCount() {
        return this.count;
    }

    public boolean areQuestionsAvailable() {
        return this.questionsAvailable;
    }
}
