package persistence;

import model.SecurityQuestion;
import model.Verification;
import model.SeedPhrase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CITATION: Based off of JsonReaderTest in JsonSerializationDemo project
 */
public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json",
                "./data/noSuchFile.json");
        try {
            ArrayList<SeedPhrase> sp = reader.readSeedPhrases();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

        try {
            Verification verification = reader.readVerification();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySeedPhrase() {
        JsonReader reader = new JsonReader("./data/testEmptySeedPhrase.json", "");
        try {
            ArrayList<SeedPhrase> sp = reader.readSeedPhrases();
            assertEquals(0, sp.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyVerification() {
        JsonReader reader = new JsonReader("", "./data/testEmptyVerification.json");
        try {
            Verification verification = reader.readVerification();
            assertEquals(0, verification.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSeedPhrase() {
        JsonReader reader = new JsonReader("./data/testSeedPhrase.json", "");
        try {
            ArrayList<SeedPhrase> sp = reader.readSeedPhrases();
            assertEquals(2, sp.size());

            SeedPhrase seed1 = sp.get(0);
            SeedPhrase seed2 = sp.get(1);
            assertEquals("bitcoin-wallet", seed1.getId());
            assertEquals("eth-wallet", seed2.getId());
            assertEquals(-1, seed1.getSecurity());
            assertEquals(1, seed2.getSecurity());

            assertEquals("time", seed1.getWordAt(0));
            assertEquals("cost", seed1.getWordAt(1));
            assertEquals("syllabus", seed1.getWordAt(2));
            assertEquals("iron", seed1.getWordAt(3));
            assertEquals("find", seed1.getWordAt(4));
            assertEquals("sync", seed1.getWordAt(5));

            assertEquals("federal", seed2.getWordAt(0));
            assertEquals("simple", seed2.getWordAt(1));
            assertEquals("mass", seed2.getWordAt(2));
            assertEquals("endorse", seed2.getWordAt(3));
            assertEquals("miracle", seed2.getWordAt(4));
            assertEquals("present", seed2.getWordAt(5));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralVerification() {
        JsonReader reader = new JsonReader("", "./data/testVerification.json");
        try {
            Verification verification = reader.readVerification();
            assertEquals(3, verification.length());

            SecurityQuestion sq1 = verification.get(0);
            SecurityQuestion sq2 = verification.get(1);
            SecurityQuestion sq3 = verification.get(2);

            assertEquals("What is mom's last name?", sq1.getQuestion());
            assertEquals("What elementary school did I go to?", sq2.getQuestion());
            assertEquals("How many friends do I have?", sq3.getQuestion());

            assertTrue(sq1.checkAnswer("smith"));
            assertTrue(sq2.checkAnswer("james"));
            assertTrue(sq3.checkAnswer("zero"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
