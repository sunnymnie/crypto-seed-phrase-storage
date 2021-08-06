package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSecurityQuestionFrame extends Frame {
    private JTextField questionField;
    private JTextField answerField;

    public AddSecurityQuestionFrame(String title) {
        super(title);

        addForum();
        addCloseButton();
    }

    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addForum() {
        JLabel questionLabel = new JLabel("Enter name of seed-phrase (ie: Bitcoin Wallet)");
        questionField = new JTextField(10);
        questionField.addActionListener(this);

        JLabel answerLabel = new JLabel("Enter seed-phrase separated with spaces");
        answerField = new JTextField(10);
        answerField.addActionListener(this);

        JButton submitButton = new JButton("Submit");
//        HireListener hireListener = new HireListener(submitButton);
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(questionLabel);
        box.add(questionField);
        box.add(answerLabel);
        box.add(answerField);
        box.add(submitButton);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String question = questionField.getText();
        String answer = answerField.getText();
        String command = e.getActionCommand();

        //Handle the New window button.
        if ("submit".equals(command)) {
            System.out.println(question);
            System.out.println(answer);

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
