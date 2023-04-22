package Backend;

public class Question {
    private String question;

    /* A String array that shall contain four answers, from which the first one should be correct */
    private String[] answers;

    public Question() {
        this.answers = new String[4];
    }

    public void setCorrectAnswer(String answer) {
        this.answers[0] = answer;
    }

    public void setFirstWrongAnswer(String answer) {
        this.answers[1] = answer;
    }

    public void setSecondWrongAnswer(String answer) {
        this.answers[2] = answer;
    }

    public void setThirdWrongAnswer(String answer) {
        this.answers[3] = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getAnswers() {
        return this.answers;
    }
}