package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Represents a list of security questions
 */
public class Verification {
    private List<SecurityQuestion> sq;

    public Verification() {
        sq = new ArrayList();
    }

    //MODIFIES: this
    //EFFECTS: adds security question given question and answer
    public void addSecurityQuestion(String question, String answer) {
        sq.add(new SecurityQuestion(question, answer));
    }

    //EFFECTS: return the number of security questions
    public int length() {
        return sq.size();
    }

    //EFFECTS: returns the security question at index
    public SecurityQuestion get(int index) {
        return sq.get(index);
    }

    //MODIFIES: this
    //EFFECTS: removes security question
    public void remove(SecurityQuestion question) {
        sq.remove(question);
    }

    //MODIFIES: this
    //EFFECTS: shuffles security questions
    public void shuffle() {
        Collections.shuffle(sq);
    }
}
