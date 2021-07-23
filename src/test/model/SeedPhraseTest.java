package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

public class SeedPhraseTest {
    SeedPhrase sp;

    @BeforeEach
    public void beforeEach() {
        List<String> s = new ArrayList();
        for (int i = 0; i < 12; i++) {
            s.add(Integer.toString(i));
        }
        sp = new SeedPhrase(s, "first phrase", 0);
    }

    @Test
    public void testChangeSecurity() {
        assertEquals(sp.getSecurity(), 0);
        sp.changeSecurity(5);
        assertEquals(sp.getSecurity(), 5);
        sp.changeSecurity(-1);
        assertEquals(sp.getSecurity(), -1);
    }

    @Test
    public void testChangeId() {
        assertTrue(sp.getId().equals("first phrase"));
        sp.changeId("new id");
        assertTrue(sp.getId().equals("new id"));
    }

    @Test
    public void testLength() {
        assertEquals(sp.length(), 12);
    }

    @Test
    public void testGetWordAt() {
        assertTrue(sp.getWordAt(0).equals("0"));
        assertTrue(sp.getWordAt(3).equals("3"));
        assertTrue(sp.getWordAt(5).equals("5"));
        assertTrue(sp.getWordAt(7).equals("7"));
    }

    @Test
    public void testGetSecurity() {
        assertEquals(sp.getSecurity(), 0);
    }

    @Test
    public void testGetId() {
        assertTrue(sp.getId().equals("first phrase"));
    }
}
