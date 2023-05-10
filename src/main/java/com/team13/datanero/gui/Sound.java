package com.team13.datanero.gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private static Sound instance;
    private Clip backgroundMusic;
    private Clip buttonClick;
    private Clip correctAnswer;
    private Clip wrongAnswer;

    public enum SoundStatus {
        ON,
        OFF
    }

    private SoundStatus soundStatus;

    private Sound() {
        try {
            AudioInputStream backgroundMusicInputStream = AudioSystem
                    .getAudioInputStream(new File("src/main/java/com/team13/datanero/sounds/backgroundmusicdemo.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(backgroundMusicInputStream);

            AudioInputStream buttonClickInputStream = AudioSystem
                    .getAudioInputStream(new File("src/main/java/com/team13/datanero/sounds/buttonclick.wav"));
            buttonClick = AudioSystem.getClip();
            buttonClick.open(buttonClickInputStream);

            AudioInputStream correctAnswerInputStream = AudioSystem
                    .getAudioInputStream(new File("src/main/java/com/team13/datanero/sounds/correctanswer.wav"));
            correctAnswer = AudioSystem.getClip();
            correctAnswer.open(correctAnswerInputStream);

            AudioInputStream wrongAnswerInputStream = AudioSystem
                    .getAudioInputStream(new File("src/main/java/com/team13/datanero/sounds/wronganswer.wav"));
            wrongAnswer = AudioSystem.getClip();
            wrongAnswer.open(wrongAnswerInputStream);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
            instance.setSoundStatus(SoundStatus.ON);
        }
        return instance;
    }

    /**
     * Getter for sound status.
     * 
     * @return Class variable soundStatus
     */
    public SoundStatus getSoundStatus() {
        return this.soundStatus;
    }

    /**
     * Method that can be used to change the sound status.
     * 
     * @param soundStatus ON or OFF
     */
    public void setSoundStatus(SoundStatus soundStatus) {
        this.soundStatus = soundStatus;
    }

    /**
     * Method that starts to loop background music.
     */
    public void startBackgroundMusic() {
        if (backgroundMusic != null && !backgroundMusic.isRunning()) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Method that stops background music.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    /**
     * Method that plays a button click sound effect.
     */
    public void playButtonClickSound() {
        if (buttonClick != null && soundStatus.equals(SoundStatus.ON)) {
            buttonClick.setFramePosition(0);
            buttonClick.start();
        }
    }

    /**
     * Method that plays a correct answer sound effect.
     */
    public void playCorrectAnswerSound() {
        if (correctAnswer != null && soundStatus.equals(SoundStatus.ON)) {
            correctAnswer.setFramePosition(0);
            correctAnswer.start();
        }
    }

    /**
     * Method that plays a wrong answer sound effect.
     */
    public void playWrongAnswerSound() {
        if (wrongAnswer != null && soundStatus.equals(SoundStatus.ON)) {
            wrongAnswer.setFramePosition(0);
            wrongAnswer.start();
        }
    }

}