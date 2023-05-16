package com.team13.datanero.backend;

public class Question {
    private LanguageHandler languageHandler;
    private String questionFi;
    private String questionEn;

    /*
     * A String array that shall contain four answers, from which the first one
     * should be correct
     */
    private String[] answersFi;
    private String[] answersEn;

    public Question() {
        this.languageHandler = LanguageHandler.getInstance();
        this.answersFi = new String[4];
        this.answersEn = new String[4];
    }

    public void setCorrectAnswerFi(String answerFi) {
        this.answersFi[0] = answerFi;
    }

    public void setCorrectAnswerEn(String answerEn) {
        this.answersEn[0] = answerEn;
    }

    public void setFirstWrongAnswerFi(String answerFi) {
        this.answersFi[1] = answerFi;
    }

    public void setFirstWrongAnswerEn(String answerEn) {
        this.answersEn[1] = answerEn;
    }

    public void setSecondWrongAnswerFi(String answerFi) {
        this.answersFi[2] = answerFi;
    }

    public void setSecondWrongAnswerEn(String answerEn) {
        this.answersEn[2] = answerEn;
    }

    public void setThirdWrongAnswerFi(String answerFi) {
        this.answersFi[3] = answerFi;
    }

    public void setThirdWrongAnswerEn(String answerEn) {
        this.answersEn[3] = answerEn;
    }

    public void setQuestionFi(String questionFi) {
        this.questionFi = questionFi;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn;
    }

    public String getQuestion() {
        if (languageHandler.getCurrentLanguage().equals("en")) {
            return this.questionEn;
        }
        return this.questionFi;
    }

    public String[] getAnswers() {
        if (languageHandler.getCurrentLanguage().equals("en")) {
            return this.answersEn;
        }
        return this.answersFi;
    }
}