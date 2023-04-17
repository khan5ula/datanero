package Backend;

public class Question {
    private String questionText;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(String questionText, String[] answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public String getFirstAnswer() {
        return this.answers[0];
    }

    public String getSecondAnswer() {
        return this.answers[1];
    }

    public String getThirdAnswer() {
        return this.answers[2];
    }

    public String getFourthAnswer() {
        return this.answers[3];
    }

    public int getCorrectAnswerIndex() {
        return this.correctAnswerIndex;
    }

    public boolean isAnswerCorrect(int answerIndex) {
        return answerIndex == this.correctAnswerIndex;
    }
}
