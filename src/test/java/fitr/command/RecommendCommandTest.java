package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RecommendCommandTest {
    @Test
    public void exitCommandTest() {
        Command recommend = new RecommendCommand("recommend");
        assertFalse(recommend.isExit());
    }
}
