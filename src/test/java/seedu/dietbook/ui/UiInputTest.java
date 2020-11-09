package seedu.dietbook.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.dietbook.exception.DietException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UiInputTest {

    private UiInput uiInput;

    @BeforeEach
    public void setUp() {
        uiInput = new UiInput();
    }

    @Test
    void processCommand_emptyCommand_expectDietException() {
        assertThrows(DietException.class, () -> uiInput.processCommand("   "));
    }

    @Test
    void processCommand_commandWithLeadingAndTrailingSpaces_trimmedCommand() throws DietException {
        assertEquals("delete 1", uiInput.processCommand("   delete 1   "));
    }

    @Test
    void processCommand_commandWithoutLeadingAndTrailingSpaces_command() throws DietException {
        assertEquals("delete 1", uiInput.processCommand("delete 1"));
    }

}