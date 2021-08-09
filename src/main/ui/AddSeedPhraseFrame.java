package ui;

import model.SeedPhrase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSeedPhraseFrame extends Frame {
    private JTextField idField;
    private JTextField seedPhraseField;
    private JTextField securityField;
    private SeedPhrase sp;

    public AddSeedPhraseFrame(String title) {
        super(title);

        addForum();
//        addCloseButton();
    }

    public AddSeedPhraseFrame(String title, SeedPhrase sp) {
        super(title);

        this.sp = sp;

        addEditForum();
//        addCloseButton();
    }

    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addForum() {
        JLabel questionID = new JLabel("Enter name of seed-phrase (ie: Bitcoin Wallet)");
        idField = new JTextField(10);
        idField.addActionListener(this);

        JLabel questionSP = new JLabel("Enter seed-phrase separated with spaces");
        seedPhraseField = new JTextField(20);
        seedPhraseField.addActionListener(this);

        JLabel questionSecurity = new JLabel("Enter security of seed-phrase");
        securityField = new JTextField(10);
        securityField.addActionListener(this);

        JButton submitButton = new JButton("Submit");
//        HireListener hireListener = new HireListener(submitButton);
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

    //MODIFIES: this
    //EFFECTS: Adds question and text fields
    private void addEditForum() {
        JLabel questionID = new JLabel("Seed-phrase name (ie: Bitcoin Wallet)");
        idField = new JTextField(10);
        idField.setText(sp.getId());
        idField.addActionListener(this);

        JLabel questionSecurity = new JLabel("Seed-phrase security");
        securityField = new JTextField(10);
        securityField.setText(Integer.toString(sp.getSecurity()));
        securityField.addActionListener(this);

        JButton submitButton = new JButton("Save");
//        HireListener hireListener = new HireListener(submitButton);
        submitButton.setActionCommand("save");
        submitButton.addActionListener(this);

        Box box = Box.createVerticalBox();

        box.add(questionID);
        box.add(idField);
        box.add(questionSecurity);
        box.add(securityField);
        box.add(submitButton);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        String seedPhrase = seedPhraseField.getText();
        String security = securityField.getText();
        String command = e.getActionCommand();

        //Handle the New window button.
        if ("submit".equals(command)) {
            System.out.println(id);
            System.out.println(seedPhrase);
            System.out.println(security);

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
