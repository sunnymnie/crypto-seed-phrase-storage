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

    public VerificationFrame(String title, SeedPhrase sp, Verification verification) {
        super(title);

        this.sp = sp;
        this.verification = verification;
        getNumQuestions();
        this.nextQuestion = 1;
        this.allCorrect = true;
        currentQuestion = verification.get(0);

        addQuestion();
//        addCloseButton();
    }

    public VerificationFrame(String title, Verification verification) {
        super(title);

        this.verification = verification;
        getNumQuestions();
        this.nextQuestion = 1;
        this.allCorrect = true;
        currentQuestion = verification.get(0);

        addQuestion();
//        addCloseButton();
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


        JButton submitButton = new JButton("Submit");

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

//    //REQUIRES: i must be a valid index in seed phrases
//    //MODIFIES: this
//    //EFFECTS: shows seed phrases and allows editing seed phrase if enough questions are solved correctly
//    private void solveSeedPhrase(int i) {
////        SeedPhrase seed = sp.get(i);
//        boolean allCorrect = true;
////        int numQuestions = seed.getSecurity();
//        int numQuestions = 3;
//        if (verification.length() > 0 || numQuestions == 0) { // If security questions is not empty & enabled questions
//            if (numQuestions == -1 || numQuestions >= verification.length()) { //If more questions than exists
//                allCorrect = runThroughQuestions(verification.length());
//            } else { // If have to pick random questions to ask
//                allCorrect = runThroughQuestions(numQuestions);
//            }
//        }
//        if (allCorrect) {
//            showSeedPhrase(seed);
//        } else {
//            System.out.println("Unfortunately, one or more of your responses are wrong");
//        }
//    }

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
            } else {
                if (allCorrect) {
//                    question.setText("Congrats!");
                    nextFrame();
                } else {
                    question.setText("One or more of your answers are incorrect!");
                }
            }
//            System.out.println(answer);
//            question.setText(answer);

        } else if ("asp".equals(command)) {
            //pass
        } else if ("vsq".equals(command)) {
            //pass
        } else if ("asq".equals(command)) {
            //pass
        } else {
            setVisible(false);
            dispose();
        }
    }

    //EFFECTS: calls menu and opens next frame
    private void nextFrame() {
        if (sp != null) {
            //pass
        } else {
//            menu.show
        }
    }


}
