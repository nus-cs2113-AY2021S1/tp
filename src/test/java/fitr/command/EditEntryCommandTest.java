package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class EditEntryCommandTest {
    @Test
    public void editEntryCommandExitTest() {
        Command editEntry = new EditEntryCommand("exercise","25/10/2020 15km run /360");
        assertFalse(editEntry.isExit());
    }
}
