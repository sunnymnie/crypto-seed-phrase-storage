package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

/*
Represents a security question with question and answer
 */

public class SecurityQuestion implements Writable {
    private String question;
    private String answer;

    public SecurityQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    //EFFECTS: Returns true if answer is correct, false otherwise
    public boolean checkAnswer(String input) {
        if (input.equalsIgnoreCase(this.answer)) {
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: Changes the question to input
    public void updateQuestion(String q) {
        this.question = q;
    }

    //MODIFIES: this
    //EFFECTS: Changes the answer to input
    public void updateAnswer(String a) {
        this.answer = a;
    }

    //EFFECTS: Returns question
    public String getQuestion() {
        return this.question;
    }

    //EFFECTS: Returns answer
    public String getAnswer() {
        return this.answer;
    }

    //EFFECTS: Returns security question as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", this.question);
        json.put("answer", this.answer);
        return json;
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
        SecurityQuestion that = (SecurityQuestion) o;
        return Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
    }

    //EFFECTS: returns hashcode
    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
