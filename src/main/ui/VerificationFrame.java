package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
CITATION: Based on ListDemoProject from Oracle Swing demo files
 */
public class VerificationFrame extends Frame {

    private JTextField answerField;
    private JLabel question;

    public VerificationFrame(String title) {
        super(title);

        addQuestion();
        addCloseButton();
    }

    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addQuestion() {
        question = new JLabel("What is asdf?");

        answerField = new JTextField(10);
        answerField.addActionListener(this);
//        submittedAnswer.getDocument().addDocumentListener(this);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();


        JButton submitButton = new JButton("Submit");
//        HireListener hireListener = new HireListener(submitButton);
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(question);
        box.add(answerField);
        box.add(submitButton);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String answer = answerField.getText();
        String command = e.getActionCommand();

        //Handle the New window button.
        if ("submit".equals(command)) {
            System.out.println(answer);
            question.setText(answer);

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


}
