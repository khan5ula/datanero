package Backend;

import java.util.List;

public class Game {
    private List<Question> questions;
    private Player player;
    private int currentQuestionIndex;

    public Game(List<Question> questions, int initialLives) {
        this.questions = questions;
        this.player = new Player(initialLives);
        this.currentQuestionIndex = 0;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean checkAnswer(int answerIndex) {
        Question question = getCurrentQuestion();
        boolean isCorrect = question.isAnswerCorrect(answerIndex);

        if (isCorrect) {
            player.incrementScore();
        } else {
            player.decrementScore();
            player.decrementLives();
        }

        currentQuestionIndex++;
        return isCorrect;
    }

    public boolean isGameOver() {
        return !player.isAlive() || currentQuestionIndex >= questions.size();
    }
}
