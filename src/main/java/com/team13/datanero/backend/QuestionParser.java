package com.team13.datanero.backend;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionParser {
    private QuestionBank questionBank;

    public QuestionParser() {
        System.out.println("Status: Creating question parser");
        this.questionBank = QuestionBank.getInstance();
    }

    /**
     * Method that executes all class methods in order to fetch questions from a JSON file.
     */
    public void execute() {
        System.out.println("Status: Initializing question parser");
        String questionsAsString = readFile("src/main/java/com/team13/datanero/questions/questions.json");
        JSONObject questionsAsJSON = stringToJsonObject(questionsAsString);
        ArrayList<Question> listOfQuestions = parseQuestions(questionsAsJSON);
        addQuestions(listOfQuestions);
    }

    private ArrayList<Question> parseQuestions(JSONObject jsonObject) {
        System.out.println("Status: Parsing questions from the JSON");
        JSONArray questionArray = jsonObject.getJSONArray("questions");

        ArrayList<Question> questions = new ArrayList<>();

        /* Go through the JSONArray and add the questions to the list */
        for (int i = 0; i < questionArray.length(); i++) {
            JSONObject questionJsonObject = questionArray.getJSONObject(i);
            JSONArray wrongAnswers = questionJsonObject.getJSONArray("wrongAnswers");

            Question question = new Question();
            question.setQuestion(questionJsonObject.getString("question"));
            question.setCorrectAnswer(questionJsonObject.getString("correctAnswer"));
            question.setFirstWrongAnswer(wrongAnswers.get(0).toString());
            question.setSecondWrongAnswer(wrongAnswers.get(1).toString());
            question.setThirdWrongAnswer(wrongAnswers.get(2).toString());
            questions.add(question);
        }
        return questions;
    }

    /**
     * Small method that adds the questions to the class variable QuestionBank.
     * @param questions ArrayList of the questions parsed from the JSON.
     */
    private void addQuestions(ArrayList<Question> questions) {
        System.out.println("Status: Adding questions to question bank");
        for (Question question : questions) {
            this.questionBank.addQuestion(question);
        }
    }

    /**
     * Reads the content from a given filename into a String.
     * @param filename String filename (and filepath).
     * @return String containing all content from the requested File.
     */
    private String readFile(String filename) {
        System.out.println("Status: Reading the questions file");
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("Error: File could not bea read: " + e.getMessage());
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Small method that converts a String to JSONObject.
     * @param jsonString, the String to be converted.
     * @return The JSONObject containing the content from the parameter String.
     */
    private JSONObject stringToJsonObject(String jsonString) {
        return new JSONObject(jsonString);
    }
    
}
