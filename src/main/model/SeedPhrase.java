package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.Objects;

/*
Represents a seed-phrase with phrase and id
 */

public class SeedPhrase implements Writable {
    private List<String> phrase;
    private String id;
    private int security;

    public SeedPhrase(List<String> phrase, String id, int security) {
        this.phrase = phrase;
        this.id = id;
        this.security = security;
    }

    //REQUIRES: security to be -1 or greater
    //MODIFIES: this
    //EFFECTS: changes security to new value
    public void changeSecurity(int security) {
        this.security = security;
    }

    //MODIFIES: this
    //EFFECTS: changes id to new value
    public void changeId(String id) {
        this.id = id;
    }

    //EFFECTS: returns the length of the seed phrase
    public int length() {
        return phrase.size();
    }

    //REQUIRES: index must be less than seed-phrase length
    //EFFECTS: returns the word at that index in the seed-phrase
    public String getWordAt(int index) {
        return phrase.get(index);
    }

    //EFFECTS: returns the security number
    public int getSecurity() {
        return this.security;
    }

    //EFFECTS: returns name of seed-phrase
    public String getId() {
        return this.id;
    }

    //EFFECTS: returns true if equal
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeedPhrase that = (SeedPhrase) o;
        return security == that.security && Objects.equals(phrase, that.phrase) && Objects.equals(id, that.id);
    }

    //EFFECTS: returns hashcode
    @Override
    public int hashCode() {
        return Objects.hash(phrase, id, security);
    }

    //EFFECTS: returns seed-phrase as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("security", this.security);
        json.put("phrase", phrasesToJson());
        return json;
    }

    // EFFECTS: returns words in phrase as a JSON array
    private JSONArray phrasesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : phrase) {
            jsonArray.put(s);
        }

        return jsonArray;
    }
}
