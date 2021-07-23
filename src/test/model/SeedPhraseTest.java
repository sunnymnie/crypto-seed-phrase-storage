package model;
import org.junit.jupiter.api.BeforeEach;
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
}
