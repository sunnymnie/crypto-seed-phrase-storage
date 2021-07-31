package persistence;

import model.SeedPhrase;
import model.Verification;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;

/*
Represents a writer that writes JSON representation of seed-phrases or verifications to file

CITATION: heavily based off of JsonWriter class in JsonSerializationDemo
 */
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter seedPhraseWriter;
    private PrintWriter verificationWriter;
    private String seedPhraseDestination;
    private String verificationDestination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String seedPhraseDestination, String verificationDestination) {
        this.seedPhraseDestination = seedPhraseDestination;
        this.verificationDestination = verificationDestination;
    }

    // MODIFIES: this
    // EFFECTS: opens seed-phrase writer and verification writer;
    // throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        seedPhraseWriter = new PrintWriter(new File(this.seedPhraseDestination));
        verificationWriter = new PrintWriter(new File(this.verificationDestination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of seed-phrases to file
    public void writeSeedPhrases(ArrayList<SeedPhrase> sp) {

        JSONArray jsonArray = new JSONArray();
        for (SeedPhrase s : sp) {
            jsonArray.put(s.toJson());
        }
        saveSeedPhaseToFile(jsonArray.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Verification to file
    public void writeVerification(Verification verification) {
        JSONArray jsonArray = verification.toJsonArray();
        saveVerificationToFile(jsonArray.toString(TAB));
    }


    // MODIFIES: this
    // EFFECTS: closes both writers
    public void close() {
        seedPhraseWriter.close();
        verificationWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes seed-phrases string to file
    private void saveSeedPhaseToFile(String json) {
        seedPhraseWriter.print(json);
    }

    // MODIFIES: this
    // EFFECTS: writes verification string to file
    private void saveVerificationToFile(String json) {
        verificationWriter.print(json);
    }
}
