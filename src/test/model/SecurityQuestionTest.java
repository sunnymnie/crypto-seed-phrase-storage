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
}
