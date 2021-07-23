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

}
