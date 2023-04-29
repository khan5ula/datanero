package com.team13.datanero.backend;

public class Score {
    private String nick;
    private int score;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score(String nick, int score) {
        this.nick = nick;
        this.score = score;
    }
}
