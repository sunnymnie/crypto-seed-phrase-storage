package model;

import java.util.Locale;

/*
Security question with question and answer
 */
public class SecurityQuestion {
    private String question;
    private String answer;

    public SecurityQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    //EFFECTS: Returns true if answer is correct, false otherwise
    public boolean checkAnswer(String input) {
        if (input.toLowerCase().equals(this.answer.toLowerCase())) {
            return true;
        }
        return false;
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
}
