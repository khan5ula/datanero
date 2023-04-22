package Backend;

public class Game {
    private Player player;
    private QuestionBank questionBank;
    private int questionsAvailable;
    private Question currentQuestion;

    public Game(int lives, QuestionBank questionBank) {
        this.player = new Player(lives);
        this.questionBank = questionBank;
        this.questionsAvailable = this.questionBank.getCount();
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
        this.currentQuestion = this.questionBank.getRandomQuestion();
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

    
}
