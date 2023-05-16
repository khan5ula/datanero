package com.team13.datanero.gui;

import java.util.Random;

/** 
 * Class for animating how text appears.
 * Source: https://gist.github.com/Da9el00/c482c89aede0f5ceff1a12b55ea65ed1#file-controller-java-L15
 */
public class TextAnimator implements Runnable {
    private String text;
    private int animationTime;
    private TextOutput textOutput;
    private Random random = new Random();

    public TextAnimator(String text, int animationTime, TextOutput textField) {
        this.text = text;
        this.animationTime = animationTime;
        this.textOutput = textField;
    }

    @Override
    public void run() {
        try {
        for (int i = 0; i <= text.length(); i++) {
            String textAtThisPoint = text.substring(0,i);
                textOutput.writeText(textAtThisPoint);
                Thread.sleep(animationTime + random.nextInt(12));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
