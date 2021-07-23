package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityQuestionTest {
    SecurityQuestion sq;

    @BeforeEach
    public void beforeEach() {
        sq = new SecurityQuestion("What is 1?", "One");
    }

    @Test
    public void testCheckAnswerCorrect() {
        assertTrue(sq.checkAnswer("One"));
        assertTrue(sq.checkAnswer("oNe"));
        assertTrue(sq.checkAnswer("onE"));
    }

    @Test
    public void testCheckAnswerIncorrect() {
        assertFalse(sq.checkAnswer("O ne"));
        assertFalse(sq.checkAnswer("onee"));
    }

    @Test
    public void testUpdateQuestion() {
        assertTrue(sq.getQuestion().equals("What is 1?"));
        sq.updateQuestion("");
        assertTrue(sq.getQuestion().equals(""));
        sq.updateQuestion("test 1");
        assertTrue(sq.getQuestion().equals("test 1"));
    }

    @Test
    public void testUpdateAnswer() {
        assertTrue(sq.checkAnswer("One"));
        sq.updateAnswer("Three");
        assertTrue(sq.checkAnswer("Three"));
        sq.updateAnswer("test 1");
        assertTrue(sq.checkAnswer("test 1"));
    }

    @Test
    public void testGetQuestion() {
        assertTrue(sq.getQuestion().equals("What is 1?"));
        sq.updateQuestion("");
        assertTrue(sq.getQuestion().equals(""));
        sq.updateQuestion("test 1");
        assertTrue(sq.getQuestion().equals("test 1"));
    }
}
