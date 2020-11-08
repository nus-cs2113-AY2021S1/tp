package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    @Test
    public void exitCommandTest() {
        Command exit = new ExitCommand("bye");
        assertTrue(exit.isExit());
    }
}
