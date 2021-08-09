package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
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


    @Test
    public void testEquals() {
        assertEquals(new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3));
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "b", "d"), "test", 3),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 2));
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "b", "d"), "test", 3),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "tst", 3));
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "b"), "test", 3),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3));
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "c"), "tests", 2),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3));
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "b", "d"), "tests", 3),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3));
        assertNotEquals(new SeedPhrase(Arrays.asList("a", "b", "c"), "tests", 2),
                new SeedPhrase(Arrays.asList("a", "b", "c"), "test", 3));
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(sp,sp);
    }

    @Test
    public void testEqualsNullObject() {
        assertNotEquals(null,sp);
        assertNotEquals(sp,null);
    }

    @Test
    public void testEqualsAnotherClassObject() {
        assertNotEquals(new SecurityQuestion("and", "ne"),sp);
        assertNotEquals(sp,new SecurityQuestion("ands", "ned"));
    }

    @Test
    public void testHashCode() {
        assertEquals(-988094275, sp.hashCode());
    }
}
