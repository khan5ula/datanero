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
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getFirstAnswer() {
        return answers[0];
    }

    public String getSecondAnswer() {
        return answers[1];
    }

    public String getThirdAnswer() {
        return answers[2];
    }

    public String getFourthAnswer() {
        return answers[3];
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isAnswerCorrect(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}
