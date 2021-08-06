package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuFrame extends Frame {


    private String vsp;
    private String asp;
    private String vsq;
    private String asq;

    public MenuFrame(String title) {
        super(title);

        setConstants();

        addMenuButtons();

        addCloseButton();
    }

    //MODIFIES: this
    //EFFECTS: sets constants for action commands
    private void setConstants() {
        vsp = "view_seed_phrases";
        asp = "add_seed_phrase";
        vsq = "view_security_questions";
        asq = "add_security_question";
    }

    //EFFECTS: Creates a new JButton with name and action command and returns it
    private JButton addButton(String name, String actionCommand) {
        JButton newButton = new JButton(name);
        newButton.setActionCommand(actionCommand);
        newButton.addActionListener(this);
        return newButton;
    }

    //MODIFIES: this
    //EFFECTS: Adds menu buttons
    private void addMenuButtons() {
        JLabel labelSP = new JLabel("Seed-phrases");
        JLabel labelSQ = new JLabel("Security questions");

        JButton buttonSP = addButton("View seed-phrases", vsp);
        JButton buttonASP = addButton("Add seed-phrase", asp);
        JButton buttonSQ = addButton("View security-questions", vsq);
        JButton buttonASQ = addButton("Add security-question", asq);

        //Add everything to a container.
        Box box = Box.createVerticalBox();
        Box box1 = Box.createVerticalBox();
        Box horizontalBox = Box.createHorizontalBox();
        box.add(labelSP);
        box.add(buttonSP);
        box.add(buttonASP);
        box1.add(labelSQ);
        box1.add(buttonSQ);
        box1.add(buttonASQ);

        horizontalBox.add(box);
        horizontalBox.add(box1);

        //Add some breathing room.
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        horizontalBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPane.add(horizontalBox, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        //Handle the New window button.
        if (vsp.equals(command)) {
            menu.showNewWindow();

        } else if (asp.equals(command)) {
            //pass
        } else if (vsq.equals(command)) {
            //pass
        } else if (asq.equals(command)) {
            //pass
        } else {
            setVisible(false);
            dispose();
        }
    }

}
