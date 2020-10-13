package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class InvalidCommandTest {
    @Test
    public void testInvalid() {
        Command invalid = new InvalidCommand("haha");
        assertFalse(invalid.isExit());
    }
}