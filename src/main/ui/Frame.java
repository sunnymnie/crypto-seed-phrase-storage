package ui;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import  java.io.*;

/*
Represents a frame that includes basic functionality for all frames in this project
 */
public abstract class Frame extends JFrame implements ActionListener {

    protected Container contentPane;
    protected VaultMenu menu;

    //Create a frame with a button.
    public Frame(String title) {
        super(title);
        menu = new VaultMenu();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,
                BoxLayout.PAGE_AXIS));
        contentPane.add(Box.createVerticalGlue()); //takes all extra space
    }

//    //MODIFIES: this
//    //EFFECTS: adds a close button
//    protected void addCloseButton() {
//        //This button lets you close even an undecorated window.
//        JButton button = new JButton("Save and quit");
//        button.addActionListener(this);
//
//        contentPane.add(button);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT); //horizontally centered
//        contentPane.add(Box.createVerticalStrut(5)); //spacer
//    }

    //EFFECTS: plays vault opening sound
    public void playVaultOpenSound() {
        playSound("vault-opening.wav");
    }

    //EFFECTS: plays verification failing sound
    public void playErrorSound() {
        playSound("error.wav");
    }

    //EFFECTS: plays sound given string file name
    public void playSound(String path) {
        /*
        CITATION: based off of: https://stackoverflow.com/a/37693420
         */
        File f = new File("data/sounds/" + path);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    //Processes command
    public abstract void actionPerformed(ActionEvent e);
//    public void actionPerformed(ActionEvent e) {
//        setVisible(false);
//        dispose();
//    }
}