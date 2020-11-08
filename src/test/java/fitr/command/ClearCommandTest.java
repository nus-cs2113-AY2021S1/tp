package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ClearCommandTest {
    @Test
    public void clearCommandTest() {
        Command clearCommand = new ClearCommand("exercise");
        assertFalse(clearCommand.isExit());
    }
}
