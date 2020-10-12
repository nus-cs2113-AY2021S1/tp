package seedu.duke.command.timetable;

import org.junit.jupiter.api.Test;
import seedu.duke.ItemList;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Slot;
import seedu.duke.slot.SlotList;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AddSlotCommandTest {

    @Test
    void constructor_invalidInput_throwsDukeException() {
        String input = AddSlotCommand.ADD_KW;

        try {
            AddSlotCommand command = new AddSlotCommand(input);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getError(), DukeExceptionType.INVALID_SLOT_INPUT);
        }

        input = AddSlotCommand.ADD_KW + "1111 12:00 mon CS1231 lecture";

        try {
            AddSlotCommand command = new AddSlotCommand(input);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getError(), DukeExceptionType.INVALID_TIME_FORMAT);
        }
    }
}