package model;

import java.util.List;
/*
Represents a seed-phrase with phrase and id
 */

public class SeedPhrase {
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



}
