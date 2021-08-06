package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Frame extends JFrame implements ActionListener {

    protected Container contentPane;
    protected VaultMenu menu;

    //Create a frame with a button.
    public Frame(String title) {
        super(title);
        menu = new VaultMenu();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setUndecorated(true);


        //Place the button near the bottom of the window.
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,
                BoxLayout.PAGE_AXIS));
        contentPane.add(Box.createVerticalGlue()); //takes all extra space
    }

    //MODIFIES: this
    //EFFECTS: adds a close button
    protected void addCloseButton() {
        //This button lets you close even an undecorated window.
        JButton button = new JButton("Save and quit");
        button.addActionListener(this);

        contentPane.add(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); //horizontally centered
        contentPane.add(Box.createVerticalStrut(5)); //spacer
    }

    //Make the button do the same thing as the default close operation
    //(DISPOSE_ON_CLOSE).
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
    }
}