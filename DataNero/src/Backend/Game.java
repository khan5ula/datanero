package Backend;

public class Game {
    private Player player;
    private QuestionBank questionBank;

    public Game(int lives, QuestionBank questionBank) {
        this.player = new Player(lives);
        this.questionBank = questionBank;
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

    
}
