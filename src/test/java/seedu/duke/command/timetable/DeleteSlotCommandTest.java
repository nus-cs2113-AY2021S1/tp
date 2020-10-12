package seedu.duke.command.timetable;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Slot;
import seedu.duke.slot.SlotList;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DeleteSlotCommandTest {

    @Test
    void constructor_invalidInput_throwsDukeException() {
        String input = DeleteSlotCommand.DEL_KW + " 0";

        try {
            DeleteSlotCommand command = new DeleteSlotCommand(input);
        } catch (DukeException e) {
            assertEquals(e.getError(), DukeExceptionType.INVALID_SLOT_NUMBER);
        }
    }
}