package com.team13.datanero.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.team13.datanero.backend.Game;
import com.team13.datanero.gui.Theme.FontStyle;

public class GameScreen extends JPanel {
    private MainFrame mainFrame;
    private Game game;
    private JButton[] answerButtons;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel heartLabel1, heartLabel2, heartLabel3;
    private JTextPane questionTextArea;
    private JLabel mascotLabel;
    private ArrayList<JButton> buttons;
    private boolean wasAnswerCorrect;
    private Theme theme;
    private String htmlFormat;
    private Sound sound;
    private boolean hoverEnabled = true;

    public GameScreen(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;
        this.mascotLabel = new JLabel();
        this.theme = Theme.getInstance();
        this.sound = Sound.getInstance();
        this.htmlFormat = "<html><body style='width: 500px; padding: 0px 20px;'>%s</body></html>";
        setBackground(theme.getScreenBackGroundColor());
        init();
    }

    /**
     * Method that initializes the game screen elements.
     */
    private void init() {
        /* Initialize the layout manager */
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets padding = new Insets(10, 10, 10, 10);

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(250, 100, 100, 100);
        setBorder(borderPadding);

        /* Create text area for the question */
        questionTextArea = new JTextPane();
        questionTextArea.setText(game.getCurrentQuestion());
        questionTextArea.setFont(theme.getCustomFont(FontStyle.MEDIUM, 56));
        questionTextArea.setForeground(theme.getGeneralTextColor());
        questionTextArea.setOpaque(false);
        questionTextArea.setEditable(false);
        questionTextArea.setFocusable(false);
        questionTextArea.setPreferredSize(new Dimension(1200, 150));
        questionTextArea.setMaximumSize(new Dimension(1200, 150));

        /* Center the question text */
        StyledDocument doc = questionTextArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        /* Create answer buttons */
        this.answerButtons = new JButton[4];
        String[] answers = game.getAnswersForCurrentQuestion();
        for (int i = 0; i < this.answerButtons.length; i++) {
            String buttonText = String.format(this.htmlFormat, answers[i]);
            final int index = i;
            this.answerButtons[i] = new CustomButton(buttonText, theme.getAnswerButtonColor(), 30, FontStyle.SEMIBOLD);
            this.answerButtons[i].addActionListener(new AnswerButtonListener(i));
            this.answerButtons[i].setPreferredSize(new Dimension(800, 300));
            this.answerButtons[i].setMaximumSize(new Dimension(800, 300));
            this.answerButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (hoverEnabled) {
                        answerButtons[index].setBackground(theme.getAnswerButtonHoverColor());
                        repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (hoverEnabled) {
                        answerButtons[index].setBackground(theme.getAnswerButtonColor());
                        repaint();
                    }
                }
            });
        }

        /* Create a list of the answer buttons and shuffle it */
        this.buttons = new ArrayList<>();
        buttons.add(this.answerButtons[0]);
        buttons.add(this.answerButtons[1]);
        buttons.add(this.answerButtons[2]);
        buttons.add(this.answerButtons[3]);

        /* Shuffle the buttons */
        Collections.shuffle(this.buttons);

        /* Create labels for score and lives */
        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        livesLabel = new JLabel("Elämät:");

        /* Create labels for score and lives */
        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        scoreLabel.setFont(theme.getCustomFont(FontStyle.RETINA, 32));

        /* Create panel for lives and hearts */
        JPanel livesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints livesPanelConstraints = new GridBagConstraints();

        /* Create and add lives label to lives panel */
        livesLabel = new JLabel("Elämät:");
        livesLabel.setFont(theme.getCustomFont(FontStyle.RETINA, 32));
        livesLabel.setForeground(theme.getGeneralTextColor());
        livesPanelConstraints.gridx = 0;
        livesPanelConstraints.gridy = 0;
        livesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        livesPanelConstraints.insets = new Insets(10, 0, 10, 10);
        livesPanel.add(livesLabel, livesPanelConstraints);

        /* Create labels for hearts */
        /* Create labels for hearts */
        URL heartIconURL = getClass().getResource("/images/heart.png");
        ImageIcon heartIcon = new ImageIcon(heartIconURL);
        this.heartLabel1 = new JLabel(heartIcon);
        this.heartLabel2 = new JLabel(heartIcon);
        this.heartLabel3 = new JLabel(heartIcon);

        /* Add heart labels to lives panel */
        livesPanelConstraints.gridx = 1;
        livesPanel.add(heartLabel1, livesPanelConstraints);

        livesPanelConstraints.gridx = 2;
        livesPanel.add(heartLabel2, livesPanelConstraints);

        livesPanelConstraints.gridx = 3;
        livesPanel.add(heartLabel3, livesPanelConstraints);

        /* Add score label to grid */
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 80, 10, 10);
        scoreLabel.setForeground(theme.getGeneralTextColor());
        add(scoreLabel, gbc);

        /* Add lives panel to grid */
        gbc.gridx = 10;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 80, 10, 10);
        livesPanel.setBackground(theme.getScreenBackGroundColor());
        add(livesPanel, gbc);

        /* Add the question label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 9;
        gbc.gridheight = 3;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = padding;
        add(questionTextArea, gbc);

        /* Add answer buttons to grid */
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        Collections.shuffle(this.buttons);
        updateAnswerButtonLayout();

        setMascot(gbc);
        setExitButton(gbc);
    }

    /**
     * Method that creates an exit button (Lopeta) that allows the player to quit
     * game.
     * 
     * @param gbc GridBagConstraints used on Game Screen.
     */
    private void setExitButton(GridBagConstraints gbc) {
        /* Create exit button */
        JButton exitButton = new CustomButton("Lopeta", theme.getExitButtonColor(), 32, FontStyle.BOLD);
        exitButton.setActionCommand("Lopeta");
        exitButton.setPreferredSize(new Dimension(150, 100));
        exitButton.setMaximumSize(new Dimension(150, 100));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(theme.getExitButtonHoverColor());
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(theme.getExitButtonColor());
                repaint();
            }
        });

        /* Define action for exit button */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        exitButton.addActionListener(buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 10;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 80, 10, 10);
        add(exitButton, gbc);
    }

    /**
     * Method that sets the default mascot image and inserts it to the grid.
     * 
     * @param gbc GridBagConstraints used on Game Screen.
     */
    private void setMascot(GridBagConstraints gbc) {
        /* Load the mascot image */
        URL mascotIconURL = getClass()
                .getResource("/images/dalle-versions/new-generation/dalle-generated-teacher-3.png");
        ImageIcon mascotIcon = new ImageIcon(mascotIconURL);

        /* Create JLabel to hold the image, and set the icon */
        this.mascotLabel.setIcon(mascotIcon);

        /* Add the Mascot JLabel to grid */
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 10;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 80, 10, 10);
        add(mascotLabel, gbc);
    }

    /**
     * Method that updates the game screen elements.
     * Should be called after every turn to initialize a new turn.
     */
    private void updateGameDisplay() {
        System.out.println("Status: Updating game display");
        String[] answers = game.getAnswersForCurrentQuestion();
        questionTextArea.setText(game.getCurrentQuestion());
        for (int i = 0; i < this.answerButtons.length; i++) {
            String buttonText = String.format(this.htmlFormat, answers[i]);
            this.answerButtons[i].setText(buttonText);
            this.answerButtons[i].setBackground(theme.getAnswerButtonColor());
        }
        Collections.shuffle(this.buttons);
        updateAnswerButtonLayout();
    }

    /**
     * Method that updates the score display current score.
     */
    private void updateScore() {
        scoreLabel.setText("Pisteet: " + game.getScore());
    }

    /**
     * Method that updates the heart icon life indicators based on the players
     * lives.
     */
    private void updateHearts(int lives) {
        URL heartlessIconURL = getClass().getResource("/images/heartless.png");
        ImageIcon heartlessIcon = new ImageIcon(heartlessIconURL);
        switch (lives) {
            case 2:
                this.heartLabel3.setIcon(heartlessIcon);
                break;
            case 1:
                this.heartLabel2.setIcon(heartlessIcon);
                break;
            case 0:
                this.heartLabel1.setIcon(heartlessIcon);
                break;
        }
    }

    /**
     * Method that updates the layout of answer buttons.
     * Should be used inside updateGameDisplay() method to refresh
     * the buttons after they've been shuffled.
     */
    private void updateAnswerButtonLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        Insets padding = new Insets(15, 15, 15, 15);
        gbc.insets = padding;

        for (int i = 0; i < buttons.size(); i++) {
            gbc.gridx = 3 + (i % 2) * 3; // Calculate the gridx value for each button
            gbc.gridy = 4 + (i / 2) * 2; // Place the buttons below the question
            add(buttons.get(i), gbc);
        }
    }

    /**
     * Method that updates the game mascot based on the success of the player.
     */
    private void updateNegativeMascot() {
        String imagePathWithSpeechBubble = "";
        String imagePath = "";
        int delay = 0; // 1000 milliseconds = 1 second

        switch (game.getLives()) {
            case 2:
                imagePathWithSpeechBubble = "/images/dalle-versions/new-generation/with_textes/mascot-hups.png";
                imagePath = "/images/dalle-versions/new-generation/dalle-generated-teacher-1.png";
                delay = 1500; // short text, short delay
                break;
            case 1:
                imagePathWithSpeechBubble = "/images/dalle-versions/new-generation/with_textes/mascot-toinenmoka.png";
                imagePath = "/images/dalle-versions/new-generation/dalle-generated-teacher-2.png";
                delay = 2200;
                break;
            case 0:
                imagePathWithSpeechBubble = "/images/dalle-versions/new-generation/with_textes/mascot-kolmasmoka.png";
                imagePath = "/images/dalle-versions/new-generation/dalle-generated-teacher-4.png";
                delay = 2200;
                break;
            default:
                imagePathWithSpeechBubble = "/images/dalle-versions/new-generation/dalle-generated-teacher-3.png";
                imagePath = "/images/dalle-versions/new-generation/dalle-generated-teacher-3.png";
                delay = 2200;
        }

        URL imagePathWithSpeechBubbleURL = getClass().getResource(imagePathWithSpeechBubble);
        URL imagePathURL = getClass().getResource(imagePath);

        ImageIcon imageIconWithSpeechBubble = new ImageIcon(imagePathWithSpeechBubbleURL);
        ImageIcon imageIcon = new ImageIcon(imagePathURL);
        mascotLabel.setIcon(imageIconWithSpeechBubble);

        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mascotLabel.setIcon(imageIcon);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Method that changes the mascot to happy one.
     */
    private void updatePositiveMascot() {
        String imagePath = "/images/dalle-versions/new-generation/dalle-generated-teacher-3.png";
        String imagePathWithSpeechBubble = "/images/dalle-versions/new-generation/with_textes/mascot-oikein.png";

        URL imagePathWithSpeechBubbleURL = getClass().getResource(imagePathWithSpeechBubble);

        ImageIcon newMascotIcon = new ImageIcon(imagePathWithSpeechBubbleURL);
        this.mascotLabel.setIcon(newMascotIcon);

        /* Change the speech bubble mascot to a standard one after a delay */
        AtomicReference<String> imagePathRef = new AtomicReference<>(imagePath);
        Timer timer = new Timer(1100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon originalMascotIcon = new ImageIcon(getClass().getResource(imagePathRef.get()));
                mascotLabel.setIcon(originalMascotIcon);
            }
        });
        timer.setRepeats(false); // Only fire the timer once
        timer.start();
    }

    /**
     * Private class used by Game Screen Class.
     * Contains the actions for pressing buttons.
     */
    private class AnswerButtonListener implements ActionListener {
        private int answerIndex;
        private int timerDelay;

        public AnswerButtonListener(int answerIndex) {
            this.answerIndex = answerIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            wasAnswerCorrect = game.submitAnswer(answerIndex);
            JButton clickedButton = answerButtons[answerIndex];

            if (wasAnswerCorrect) {
                sound.playCorrectAnswerSound();
                clickedButton.setBackground(theme.getCorrectAnswerButtonColor());
                game.incrementScore();
                updatePositiveMascot();
                updateScore();
                this.timerDelay = 1200;
            } else {
                sound.playWrongAnswerSound();
                clickedButton.setBackground(theme.getIncorrectAnswerButtonColor());
                answerButtons[0].setBackground(theme.getCorrectAnswerButtonColor());
                game.decrementLives();
                updateNegativeMascot();
                updateHearts(game.getLives());
                updateScore();

                /*
                 * Set delay depending on the situation.
                 * If player has lives left, create moderate delay.
                 * If player lost the game, give longer delay.
                 */
                if (game.getLives() > 0) {
                    this.timerDelay = 2200;
                } else {
                    this.timerDelay = 3000;
                }
            }

            for (JButton button : answerButtons) {
                button.setEnabled(false); // Disable the buttons
            }

            /* Temporarily disable hover effect */
            hoverEnabled = false;

            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (game.getLives() <= 0 || !game.areQuestionsAvailable()) {
                        /* Game over. Player ran out of lives or the game ran out of questions. */
                        if (game.getLives() == 0) {
                            System.out.println("Status: Player has 0 lives left. Game over.");
                            updateNegativeMascot();
                            livesLabel.setText("Elämät:");
                            scoreLabel.setText("Pisteet: " + game.getScore());
                        }

                        /* Show the GameOverDialog with the score */
                        mainFrame.switchTo("GameOverScreen");
                        System.out.println("Status: Switching to game over screen");

                    } else {
                        /* There are questions available, continue game */
                        game.getNewQuestion();
                        updateGameDisplay();
                        for (JButton button : answerButtons) {
                            button.setEnabled(true); // Enable the buttons
                        }

                        /* Enable hover effect */
                        hoverEnabled = true;
                    }
                }
            };
            Timer timer = new javax.swing.Timer(timerDelay, taskPerformer);
            timer.setRepeats(false); // Make the timer execute only once
            timer.start();
        }
    }

}