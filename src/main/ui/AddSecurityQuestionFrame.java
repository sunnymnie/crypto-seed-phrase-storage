package ui;

import model.SecurityQuestion;
import model.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSecurityQuestionFrame extends Frame {
    private JTextField questionField;
    private JTextField answerField;
    private SecurityQuestion sq;
    private SecurityQuestionsFrame sqf;

    public AddSecurityQuestionFrame(String title, SecurityQuestionsFrame sqf) {
        super(title);
        this.sqf = sqf;
        addForum();
    }

    public AddSecurityQuestionFrame(String title, SecurityQuestion sq, SecurityQuestionsFrame sqf) {
        super(title);
        this.sq = sq;
        this.sqf = sqf;
        addForum();
        addValues();
    }

    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addForum() {
        JLabel questionLabel = new JLabel("Security Question");
        questionField = new JTextField(10);
        questionField.addActionListener(this);

        JLabel answerLabel = new JLabel("Answer (recommend only one word, no space)");
        answerField = new JTextField(10);
        answerField.addActionListener(this);

        JButton submitButton = new JButton("Save");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);

        JButton delete = new JButton("Delete");
        delete.setActionCommand("delete");
        delete.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(questionLabel);
        box.add(questionField);
        box.add(answerLabel);
        box.add(answerField);
        box.add(submitButton);
        box.add(delete);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    //MODIFIES: this
    //EFFECTS: fills in text fields with current values
    private void addValues() {
        this.questionField.setText(sq.getQuestion());
        this.answerField.setText(sq.getAnswer());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String question = questionField.getText();
        String answer = answerField.getText();


        String command = e.getActionCommand();

        if ("submit".equals(command)) {
            if (sq != null) {
                menu.verification.remove(sq);
            }
            menu.verification.addSecurityQuestion(question, answer);
        } else if ("delete".equals(command)) {
            menu.verification.remove(sq);

        }
        menu.saveAndUpdate();
        sqf.updateList();
        setVisible(false);
        dispose();
    }
}
