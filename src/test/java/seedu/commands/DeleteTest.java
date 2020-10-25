package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTest {


    @Test
    public void constructor_noRawInput_throws_InvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> new Delete(""));
    }

    @Test
    public void deleteCommand_invalidData() {
        final String[] invalidInputs = {"", " ", "&*^%*&^", "abc"};
        for (int i = 0; i < invalidInputs.length; i++) {
            int finalI = i;
            assertThrows(InvalidCommandException.class, () -> new Add("delete " + invalidInputs[finalI]));
        }
    }
}