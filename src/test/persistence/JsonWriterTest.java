package persistence;

import model.SecurityQuestion;
import model.SeedPhrase;
import model.Verification;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CITATION: Based off of JsonWriterTest in JsonSerializationDemo project
 */
public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json",
                    "./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySeedPhrase() {
        try {
            ArrayList<SeedPhrase> sp = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testEmptySeedPhrase.json",
                    "./data/testEmptyVerification.json");
            writer.open();
            writer.writeSeedPhrases(sp);
            writer.writeVerification(new Verification());
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptySeedPhrase.json",
                    "./data/testEmptyVerification.json");
            sp = reader.readSeedPhrases();
            assertEquals(0, sp.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyVerification() {
        try {
            Verification verification = new Verification();
            JsonWriter writer = new JsonWriter("./data/testEmptySeedPhrase.json",
                    "./data/testEmptyVerification.json");
            writer.open();
            writer.writeVerification(verification);
            writer.writeSeedPhrases(new ArrayList<SeedPhrase>());
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptySeedPhrase.json",
                    "./data/testEmptyVerification.json");
            verification = reader.readVerification();
            assertEquals(0, verification.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    //NOTE to TA: writer writes to a different destination, thus 'writer' json files are
    //            never changed, and can be used as replacement for instantiating a new
    //            arraylist of seed-phrases.
    //            'writer' json files are: testWriterSeedPhrase.json, testWriterVerification.json
    @Test
    void testWriterGeneralSeedPhrases() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterSeedPhrase.json",
                    "./data/testWriterVerification.json");

            ArrayList<SeedPhrase> sp = reader.readSeedPhrases();
            Verification verification = reader.readVerification();

            JsonWriter writer = new JsonWriter("./data/testSeedPhrase.json",
                    "./data/testVerification.json");
            writer.open();
            writer.writeSeedPhrases(sp);
            writer.writeVerification(verification);
            writer.close();

            reader = new JsonReader("./data/testSeedPhrase.json",
                    "./data/testVerification.json");
            sp = reader.readSeedPhrases();

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
            fail("Exception should not have been thrown");
        }
    }

    //NOTE to TA: writer writes to a different destination, thus 'writer' json files are
    //            never changed, and can be used as replacement for instantiating a new
    //            Verification.
    //            'writer' json files are: testWriterSeedPhrase.json, testWriterVerification.json
    @Test
    void testWriterGeneralVerification() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterSeedPhrase.json",
                    "./data/testWriterVerification.json");

            Verification verification = reader.readVerification();
            ArrayList<SeedPhrase> sp = reader.readSeedPhrases();

            JsonWriter writer = new JsonWriter("./data/testSeedPhrase.json",
                    "./data/testVerification.json");
            writer.open();
            writer.writeVerification(verification);
            writer.writeSeedPhrases(sp);
            writer.close();

            reader = new JsonReader("./data/testSeedPhrase.json",
                    "./data/testVerification.json");
            verification = reader.readVerification();

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
            fail("Exception should not have been thrown");
        }
    }
}
