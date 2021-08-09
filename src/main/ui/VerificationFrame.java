package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import model.SecurityQuestion;
import model.SeedPhrase;
import model.Verification;

/*
CITATION: Based on ListDemoProject from Oracle Swing demo files
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

    public VerificationFrame(String title, SeedPhrase sp, Verification verification) {
        super(title);

        this.sp = sp;
        init(verification);
//        addCloseButton();
    }

    public VerificationFrame(String title, Verification verification) {
        super(title);
        init(verification);
//        addCloseButton();
    }

    //MODIFIES: this
    //EFFECTS: inits fields
    private void init(Verification verification) {
        this.verification = menu.verification;
        if (verification.length() > 0) {
            menu.test();

            getNumQuestions();
            this.nextQuestion = 1;
            this.allCorrect = true;
            currentQuestion = verification.get(0);

            addQuestion();

            menu.positionFrame(this, 500, 100);
        } else {
            nextFrame();
        }
    }

    //REQUIRES: seed-phrase security to be one or greater
    //MODIFIES: this
    //EFFECTS: interprets seed-phrase security to gat number of questions to do
    private void getNumQuestions() {
        if (sp != null) {
            int security = sp.getSecurity();
            if (security == -1) {
                this.numQuestions = verification.length();
            } else if (security > verification.length()) {
                this.numQuestions = verification.length();
            }
        } else {
            this.numQuestions = verification.length();
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addQuestion() {
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

//        getRootPane().setDefaultButton(submitButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String answer = answerField.getText();
        String command = e.getActionCommand();

        //Handle the New window button.
        if ("submit".equals(command)) {

            if (!currentQuestion.checkAnswer(answer)) {
                allCorrect = false;
            }
            answerField.setText("");
            if (this.nextQuestion < this.numQuestions) {
                currentQuestion = this.verification.get(nextQuestion);
                question.setText(currentQuestion.getQuestion());
                this.nextQuestion += 1;
                answerField.requestFocusInWindow();
            } else {
                if (allCorrect) {
//                    question.setText("Congrats!");
                    nextFrame();
                } else {
                    question.setText("One or more of your answers are incorrect!");
                    submitButton.setText("Close");
                    submitButton.setActionCommand("close");
                    playErrorSound();
                }
            }

//            System.out.println(answer);
//            question.setText(answer);

        } else if ("close".equals(command)) {
            setVisible(false);
            dispose();
        }
    }

    //EFFECTS: calls menu and opens next frame and disposes current frame
    private void nextFrame() {
        if (sp != null) {
            menu.showSeedPhrase(sp);
            playVaultOpenSound();
        } else {
            menu.showSecurityQuestionsFrame();
            playVaultOpenSound();
        }
        setVisible(false);
        dispose();
    }


}
