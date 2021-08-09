package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
represents a frame for displaying the menu
 */

public class MenuFrame extends Frame {

    private static final String VSP = "seed_phrases";
    private static final String VSQ = "security_questions";

    public MenuFrame(String title) {
        super(title);
        addMenuButtons();
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
        JButton buttonSP = addButton("seed-phrases", VSP);
        JButton buttonSQ = addButton("security-questions", VSQ);

        JPanel box = new JPanel();

        box.add(buttonSP);
        box.add(buttonSQ);

        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(box, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: processes command and shows new frames
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (VSP.equals(command)) {
            menu.showSeedPhrasesWindow();
        } else if (VSQ.equals(command)) {
            menu.showVerificationWindow();
        }
    }

}
