package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuFrame extends Frame {

    private static final String VSP = "seed_phrases";
//    private static final String ASP = "add_seed_phrase";
    private static final String VSQ = "security_questions";
//    private static final String ASQ = "add_security_question";


    public MenuFrame(String title) {
        super(title);

        addMenuButtons();

        addCloseButton();
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

        JButton buttonSP = addButton("seed-phrases", VSP);
//        JButton buttonASP = addButton("Add seed-phrase", ASP);
        JButton buttonSQ = addButton("security-questions", VSQ);
//        JButton buttonASQ = addButton("Add security-question", ASQ);

        //Add everything to a container.
//        Box box = Box.createVerticalBox();
        JPanel box = new JPanel();
//        Box box1 = Box.createVerticalBox();
//        Box horizontalBox = Box.createHorizontalBox();
//        box.add(labelSP);
//        box.add(buttonSP);
//        box.add(buttonASP);
//        box1.add(labelSQ);
//        box1.add(buttonSQ);
//        box1.add(buttonASQ);

//        horizontalBox.add(box);
//        horizontalBox.add(box1);

//        box.add(labelSP);
        box.add(buttonSP);
//        box.add(labelSQ);
        box.add(buttonSQ);

        //Add some breathing room.
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        horizontalBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPane.add(box, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        //Handle the New window button.
        if (VSP.equals(command)) {
            menu.showSeedPhrasesWindow();

//        } else if (ASP.equals(command)) {
//            menu.showNewWindow();
        } else if (VSQ.equals(command)) {
            menu.showVerificationWindow();
            //pass
//        } else if (ASQ.equals(command)) {
//            //pass
        } else {
            setVisible(false);
            dispose();
        }
    }

}
