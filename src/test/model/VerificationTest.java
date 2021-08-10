package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerificationTest {
    Verification verification;

    @BeforeEach
    public void beforeEach() {
        verification = new Verification();
        verification.addSecurityQuestion("Question string?0", "answer0");
    }

    @Test
    public void testAddSecurityQuestion() {
        verification.addSecurityQuestion("Question string?1", "answer1");
        assertEquals(verification.length(), 2);
        assertTrue(verification.get(1).getQuestion().equals("Question string?1"));
        assertTrue(verification.get(1).checkAnswer("answer1"));
    }

    @Test
    public void testLength() {
        assertEquals(verification.length(), 1);
    }

    @Test
    public void testGetPossible() {
        try {
            assertEquals(verification.length(), 1);
            assertTrue(verification.get(0).getQuestion().equals("Question string?0"));
            assertTrue(verification.get(0).checkAnswer("answer0"));
            verification.addSecurityQuestion("Question string?1", "answer1");
            assertEquals(verification.length(), 2);
            assertTrue(verification.get(1).getQuestion().equals("Question string?1"));
            assertTrue(verification.get(1).checkAnswer("answer1"));
        } catch (NegativeIndexException e) {
            fail("Should work");
        }
    }

    @Test
    public void testGetNegative() {
        try {
            verification.get(-1);
            fail("Should not reach here");
        } catch (NegativeIndexException e) {
            //pass
        } catch (Exception e) {
            fail("Should not reach here");
        }
    }

    @Test
    public void testGetNegativeTwo() {
        try {
            verification.get(-2);
            fail("Should not reach here");
        } catch (NegativeIndexException e) {
            //pass
        } catch (Exception e) {
            fail("Should not reach here");
        }
    }

    @Test
    public void testRemove() {
        assertEquals(verification.length(), 1);
        verification.remove(verification.get(0));
        assertEquals(verification.length(), 0);
    }

    @Test
    public void testShuffle() {
        verification.addSecurityQuestion("Question string?1", "answer1");
        verification.addSecurityQuestion("Question string?2", "answer2");
        verification.addSecurityQuestion("Question string?3", "answer3");
        verification.addSecurityQuestion("Question string?4", "answer4");
        verification.addSecurityQuestion("Question string?5", "answer5");
        verification.addSecurityQuestion("Question string?6", "answer6");
        verification.addSecurityQuestion("Question string?7", "answer7");
        verification.addSecurityQuestion("Question string?8", "answer8");
        verification.addSecurityQuestion("Question string?9", "answer9");
        assertEquals(verification.length(), 10);
        boolean isDifferent = false;
        while (!isDifferent) {
            verification.shuffle();
            assertEquals(verification.length(), 10);
            for (int i = 0; i < 10; i++) {
                if (!verification.get(i).getQuestion().equals("Question string?" + Integer.toString(i))) {
                    isDifferent = true;
                }
            }
        }
        assertEquals(verification.length(), 10);
        assertTrue(isDifferent);
    }

}
