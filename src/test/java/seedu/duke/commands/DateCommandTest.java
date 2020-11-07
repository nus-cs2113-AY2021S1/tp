package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.item.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateCommandTest extends CommandTest {

    @Test
    void execute_validDate_setsNewDate() throws DukeException {
        resetFields();

        tasks.addTaskFromString(TEST_DESCRIPTION);
        argumentsMap.put("date", "13-05-2020");
        Command dateCommand = new DateCommand(1, argumentsMap);

        dateCommand.execute(model);
        assertEquals("13 May 2020", tasks.get(0).getDateString(Task.DATETIME_PRINT_FORMAT));
    }

    @Test
    void execute_invalidDate_throwsException() {
        resetFields();

        tasks.addTaskFromString(TEST_DESCRIPTION);
        argumentsMap.put("date", "xx-yy-zzzz");
        Command dateCommand = new DateCommand(1, argumentsMap);

        assertThrows(DukeException.class, () -> dateCommand.execute(model));
    }

    @Test
    void execute_noDate_throwsException() {
        resetFields();

        tasks.addTaskFromString(TEST_DESCRIPTION);
        Command dateCommand = new DateCommand(1, argumentsMap);

        assertThrows(DukeException.class, () -> dateCommand.execute(model));
    }
}