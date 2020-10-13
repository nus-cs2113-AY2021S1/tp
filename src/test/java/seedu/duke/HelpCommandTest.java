package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void TestHelp() {
        Command help = new HelpCommand("help");
        assertEquals(false, help.isExit());
    }
}