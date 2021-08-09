package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    public void testGetAnswer() {
        assertEquals("One", sq.getAnswer());
        sq.updateAnswer("Three");
        assertEquals("Three", sq.getAnswer());
        sq.updateAnswer("test 1");
        assertEquals("test 1", sq.getAnswer());
    }

    @Test
    public void testEquals() {
        assertEquals(new SecurityQuestion("and", "ne"),
                new SecurityQuestion("and", "ne"));
        assertNotEquals(new SecurityQuestion("and", "ne"),
                new SecurityQuestion("and", "ned"));
        assertNotEquals(new SecurityQuestion("sand", "ne"),
                new SecurityQuestion("and", "ned"));
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(sq,sq);
    }

    @Test
    public void testEqualsNullObject() {
        assertNotEquals(null,sq);
        assertNotEquals(sq,null);
    }

    @Test
    public void testEqualsAnotherClassObject() {
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3),sq);
        assertNotEquals(sq,new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3));
    }

    @Test
    public void testHashCode() {
        assertEquals(1843293791, sq.hashCode());
    }
}
