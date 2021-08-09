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

/*
CITATION: Main structure of VaultMenu is based on FrameDemo2 from Oracle Swing demo files

Class represents the main menu that loads and saves information
 */

public class VaultMenu extends WindowAdapter {

    private static final String JSON_STORE_SEEDPHRASE = "./data/seedphrase.json";
    private static final String JSON_STORE_VERIFICATION = "./data/verification.json";
    protected ArrayList<SeedPhrase> sp;
    protected Verification verification;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Point lastLocation = null;
    private int maxX;
    private int maxY;

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

    //MODIFIES: this
    //EFFECTS: shows menu frame
    public static void createAndShowGUI() {

        Frame frame = new MenuFrame("Crypto Seed-Phrase Vault");
        frame.pack();
        frame.setLocationRelativeTo(null); //center it
        frame.setVisible(true);
    }


    //EFFECTS: Shows security question frame
    public void showSecurityQuestionsFrame() {
        Frame frame = new SecurityQuestionsFrame("Security Questions");

        positionFrame(frame, 300, 200);
    }

    //EFFECTS: shows verification frame that leads to select seed-phrase frame
    public void showVerificationWindow(SeedPhrase sp) {
        new VerificationFrame(sp.getId(), sp);
    }

    //EFFECTS: shows verification frame that leads to security questions frame
    public void showVerificationWindow() {
        new VerificationFrame("Security Questions");
    }

    //EFFECTS: shows seed phrase frame
    public void showSeedPhrase(SeedPhrase sp) {
        Frame frame = new SeedPhraseSelectFrame(sp.getId(), sp);
        positionFrame(frame, 350, 150);
    }

    //MODIFIES: this
    //EFFECTS: saves and updates seed-phrase and security questions
    public void saveAndUpdate() {
        saveSeedPhrasesAndVerification();
        init();
    }

    //EFFECTS: returns saved verification
    public Verification getLatestVerification() {
        loadVerification();
        return this.verification;
    }

    //MODIFIES: this
    //EFFECT: refresh fields and show a verification Frame object for seed-phrase
    public void showSeedPhrasesWindow() {
        init();
        Frame frame = new SeedPhrasesFrame("Seed-Phrases", sp);

        positionFrame(frame, 300, 300);
    }

    //MODIFIES: frame
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

        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);
    }
}
