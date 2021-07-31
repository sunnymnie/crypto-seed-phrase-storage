package persistence;

import model.SeedPhrase;
import model.Verification;


import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

/*
Represents a reader that reads workroom from JSON data stored in file

CITATION: Heavily based off of JsonReader class in JsonSerializationDemo
 */

public class JsonReader {
    private String seedPhraseSource;
    private String securityQuestionSource;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String seedPhraseSource, String securityQuestionSource) {
        this.seedPhraseSource = seedPhraseSource;
        this.securityQuestionSource = securityQuestionSource;
    }

    // EFFECTS: reads list of seed-phrases from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<SeedPhrase> readSeedPhrases() throws IOException {
        String jsonData = readFile(seedPhraseSource);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseSeedPhrases(jsonArray);
    }

    // EFFECTS: reads verification from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Verification readVerification() throws IOException {
        String jsonData = readFile(securityQuestionSource);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseVerification(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of seed-phrases from JSON object and returns it
    private ArrayList<SeedPhrase> parseSeedPhrases(JSONArray jsonArray) {
        ArrayList<SeedPhrase> sp = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextSeedPhrase = (JSONObject) json;
            addSeedPhrase(sp, nextSeedPhrase);
        }
        return sp;
    }

    // MODIFIES: sp
    // EFFECTS: parses seed-phrase from JSON object and adds it to arraylist
    private void addSeedPhrase(ArrayList<SeedPhrase> sp, JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        int security = jsonObject.getInt("security");
        JSONArray jsonArray = jsonObject.getJSONArray("phrase");
        ArrayList<String> phrase = getPhrase(jsonArray);
        sp.add(new SeedPhrase(phrase, id, security));
    }

    // EFFECTS: parses phrase from JSON object and returns it
    private ArrayList<String> getPhrase(JSONArray jsonArray) {
        ArrayList<String> phrase = new ArrayList<>();
        for (Object json : jsonArray) {
            phrase.add(json.toString());
        }
        return phrase;
    }

    // EFFECTS: parses list of security-questions from JSON object and returns it
    private Verification parseVerification(JSONArray jsonArray) {
        Verification verification = new Verification();

        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addSecurityQuestion(verification, nextQuestion);
        }
        return verification;
    }

    // MODIFIES: verification
    // EFFECTS: parses seed-phrase from JSON object and adds it to arraylist
    private void addSecurityQuestion(Verification verification, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        verification.addSecurityQuestion(question, answer);
    }

}
