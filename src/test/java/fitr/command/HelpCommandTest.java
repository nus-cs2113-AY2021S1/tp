package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class HelpCommandTest {
    @Test
    public void testHelp() {
        Command help = new HelpCommand("help");
        assertFalse(help.isExit());
    }
}