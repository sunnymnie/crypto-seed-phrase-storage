package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.SecurityQuestion;
import model.SeedPhrase;
import model.Verification;

/*
CITATION: Based on ListDemoProject from Oracle Swing demo files

Class represents a verification frame
 */
public class VerificationFrame extends Frame {

    private SeedPhrase sp;
    private Verification verification;
    private int numQuestions;
    private int nextQuestion;
    private boolean allCorrect;
    private SecurityQuestion currentQuestion;

    private JTextField answerField;
    private JLabel question;
    private JButton submitButton;

    public VerificationFrame(String title, SeedPhrase sp) {
        super(title);

        this.sp = sp;
        init();
    }

    public VerificationFrame(String title) {
        super(title);
        init();
    }

    //MODIFIES: this
    //EFFECTS: initializes fields
    private void init() {
        this.verification = menu.verification;
        if (verification.length() > 0) {

            getNumQuestions();
            this.nextQuestion = 1;
            this.allCorrect = true;
            currentQuestion = verification.get(0);

            initQuestionAndAnswerFields();

            menu.positionFrame(this, 500, 100);
        } else {
            nextFrame();
        }
    }

    //REQUIRES: valid seed-phrase security integer in range [-1, ...)
    //MODIFIES: this
    //EFFECTS: interprets seed-phrase security to get number of questions to do
    private void getNumQuestions() {
        if (sp != null) {
            int security = sp.getSecurity();
            if (security == -1) {
                this.numQuestions = verification.length();
            } else if (security > verification.length()) {
                this.numQuestions = verification.length();
            } else {
                this.numQuestions = security;
            }
        } else {
            this.numQuestions = verification.length();
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes question and text fields
    private void initQuestionAndAnswerFields() {
        question = new JLabel(verification.get(0).getQuestion());

        answerField = new JTextField(10);
        answerField.addActionListener(this);


        submitButton = new JButton("Submit");

        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(question);
        box.add(answerField);
        box.add(submitButton);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    //MODIFIES: this
    //EFFECTS: displays next question
    private void updateQuestion() {
        currentQuestion = this.verification.get(nextQuestion);
        question.setText(currentQuestion.getQuestion());
        this.nextQuestion += 1;
        answerField.requestFocusInWindow();
    }


    //MODIFIES: this, menu
    //EFFECTS: processes user answers to security questions
    @Override
    public void actionPerformed(ActionEvent e) {
        String answer = answerField.getText();
        String command = e.getActionCommand();

        if ("submit".equals(command)) {

            if (!currentQuestion.checkAnswer(answer)) {
                allCorrect = false;
            }
            answerField.setText("");
            if (this.nextQuestion < this.numQuestions) {
                updateQuestion();
            } else {
                if (allCorrect) {
                    nextFrame();
                } else {
                    question.setText("One or more of your answers are incorrect!");
                    submitButton.setText("Close");
                    submitButton.setActionCommand("close");
                    playErrorSound();
                }
            }

        } else if ("close".equals(command)) {
            setVisible(false);
            dispose();
        }
    }

    //MODIFIES: this
    //EFFECTS: calls menu and opens next frame and disposes current frame
    private void nextFrame() {
        if (sp != null) {
            menu.showSeedPhrase(sp);
        } else {
            menu.showSecurityQuestionsFrame();
        }
        playVaultOpenSound();
        setVisible(false);
        dispose();
    }


}
