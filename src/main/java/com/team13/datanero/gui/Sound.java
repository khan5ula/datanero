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
    Clip clip;
    String audioFilePath;

    public enum SoundStatus {
        ON,
        OFF
    }

    private SoundStatus soundStatus;

    private Sound() {
    }

    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
            instance.setSoundStatus(SoundStatus.ON);
        }
        return instance;
    }

    public SoundStatus getSoundStatus() {
        return this.soundStatus;
    }

    public void setSoundStatus(SoundStatus sound) {
        this.soundStatus = sound;
    }

    public void setAudioFile(String audioFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}