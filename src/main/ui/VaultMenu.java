package ui;

import model.SecurityQuestion;
import model.SeedPhrase;
import model.Verification;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
CITATION: Main structure of VaultMenu is based on FrameDemo2 from Oracle Swing demo files
 */

public class VaultMenu extends WindowAdapter implements ActionListener {

    private static final String JSON_STORE_SEEDPHRASE = "./data/seedphrase.json";
    private static final String JSON_STORE_VERIFICATION = "./data/verification.json";
    protected ArrayList<SeedPhrase> sp;
    protected Verification verification;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Point lastLocation = null;
    private int maxX = 500;
    private int maxY = 500;

    //the main frame's default button
    private static JButton defaultButton = null;

    //Perform some initialization.
    public VaultMenu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        maxX = screenSize.width - 50;
        maxY = screenSize.height - 50;
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes seed-phrases and security questions
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE_SEEDPHRASE, JSON_STORE_VERIFICATION);
        jsonReader = new JsonReader(JSON_STORE_SEEDPHRASE, JSON_STORE_VERIFICATION);
        loadSeedPhrases();
        loadVerification();
    }

    // MODIFIES: this
    // EFFECTS: loads seed-phrases from file
    private void loadSeedPhrases() {
        try {
            this.sp = jsonReader.readSeedPhrases();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SEEDPHRASE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads verification from file
    private void loadVerification() {
        try {
            this.verification = jsonReader.readVerification();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_VERIFICATION);
        }
    }

    // EFFECTS: saves the seed-phrases and verification to file
    private void saveSeedPhrasesAndVerification() {
        try {
            jsonWriter.open();
            jsonWriter.writeSeedPhrases(sp);
            jsonWriter.writeVerification(verification);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        //Instantiate the controlling class.
        Frame frame = new MenuFrame("Crypto Seed-Phrase Vault");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        VaultMenu demo = new VaultMenu();

        //Add components to it.
//        Container contentPane = frame.getContentPane();
//        contentPane.add(demo.createOptionControls(),
//                BorderLayout.CENTER);
//        contentPane.add(demo.createButtonPane(),
//                BorderLayout.PAGE_END);
//        frame.getRootPane().setDefaultButton(defaultButton);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null); //center it
        frame.setVisible(true);
    }



    //Create a new MyFrame object and show it.
    public void showNewWindow() {
//        Frame frame = new AddSecurityQuestionFrame("New title for window", verification.get(2));

//        positionFrame(frame, 300, 200);
    }

    public void showSecurityQuestionsFrame() {
        Frame frame = new SecurityQuestionsFrame("Security Questions");

        positionFrame(frame, 300, 200);
    }

    //Create a new Verification Frame object for seed-phrase and show it.
    public void showVerificationWindow(SeedPhrase sp) {
        Frame frame = new VerificationFrame(sp.getId(), sp, verification);
    }

    //Create a new Verification Frame object for security-questions and show it.
    public void showVerificationWindow() {
        Frame frame = new VerificationFrame("Security Questions", verification);
    }

    public void showSeedPhrase(SeedPhrase sp) {
        Frame frame = new SeedPhraseSelectFrame(sp.getId(), sp);
        positionFrame(frame, 350, 150);
    }

    public void test() {
//        this.verification = verification;
        System.out.println("==========================");
        System.out.println(verification);
        for (int i = 0; i < verification.length(); i++) {
            System.out.println(verification.get(i).getQuestion());
        }
    }

    public void saveAndUpdate() {
        saveSeedPhrasesAndVerification();
        init();
    }

    public Verification getLatestVerification() {
        loadVerification();
        return this.verification;
    }

    public ArrayList<SeedPhrase> getLatestSeedPhrase() {
        loadSeedPhrases();
        return this.sp;
    }

    public void addSecurityQuestion(String question, String answer) {
        this.verification.addSecurityQuestion(question, answer);
    }

    public void deleteSecurityQuestion(SecurityQuestion sq) {
        this.verification.remove(sq);
    }

    //Create a new Verification Frame object for seed-phrase and show it.
    public void showSeedPhrasesWindow() {
        init();
        Frame frame = new SeedPhrasesFrame("Seed-Phrases", sp);

        positionFrame(frame, 300, 300);
    }

    //EFFECTS: positions frame with width and height
    public void positionFrame(JFrame frame, int width, int height) {
        //Set window location.
        if (lastLocation != null) {
            //Move the window over and down 40 pixels.
            lastLocation.translate(40, 40);
            if ((lastLocation.x > maxX) || (lastLocation.y > maxY)) {
                lastLocation.setLocation(0, 0);
            }
            frame.setLocation(lastLocation);
        } else {
            lastLocation = frame.getLocation();
        }

        //Show window.
        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);
    }

    @Override
    //Handle action events from all the buttons.
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        //Handle the New window button.
        if ("cw".equals(command)) {
            showNewWindow();

            //Handle the first group of radio buttons.
        } else if ("asdf".equals(command)) {
            JFrame.setDefaultLookAndFeelDecorated(false);
        }
    }

}
