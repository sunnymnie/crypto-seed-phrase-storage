package ui;

import model.SeedPhrase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

/*
A frame to add a new seed-phrase
 */
public class AddSeedPhraseFrame extends Frame {
    private JTextField idField;
    private JTextField seedPhraseField;
    private JTextField securityField;

    public AddSeedPhraseFrame(String title) {
        super(title);
        addForum();
    }


    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addForum() {
        JLabel questionID = new JLabel("Enter name of seed-phrase (ie: Bitcoin Wallet)");
        idField = new JTextField(10);
        idField.addActionListener(this);

        JLabel questionSP = new JLabel("Enter seed-phrase separated with commas");
        seedPhraseField = new JTextField(20);
        seedPhraseField.addActionListener(this);

        JLabel questionSecurity = new JLabel("Enter security of seed-phrase");
        securityField = new JTextField(10);
        securityField.addActionListener(this);

        JButton submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(questionID);
        box.add(idField);
        box.add(questionSP);
        box.add(seedPhraseField);
        box.add(questionSecurity);
        box.add(securityField);
        box.add(submitButton);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    //MODIFIES: menu
    //EFFECTS: processes command for submit
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        String seedPhrase = seedPhraseField.getText();
        String security = securityField.getText();

        List<String> phrase = Arrays.asList(seedPhrase.split("\\s*,\\s*"));

        menu.sp.add(new SeedPhrase(phrase, id, Integer.parseInt(security)));
        menu.saveAndUpdate();

        setVisible(false);
        dispose();
    }

}
