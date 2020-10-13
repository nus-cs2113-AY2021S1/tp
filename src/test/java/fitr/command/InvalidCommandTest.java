package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class InvalidCommandTest {
    @Test
    public void TestHelp() {
        Command invalid = new HelpCommand("haha");
        assertFalse(invalid.isExit());
    }
}