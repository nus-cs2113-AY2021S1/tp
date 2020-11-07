package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.Model;
import seedu.duke.model.ListType;
import seedu.duke.model.item.Task;
import seedu.duke.model.itemlist.TaskList;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateCommandTest {
    private static final String TEST_DESCRIPTION = "test description";
    private HashMap<String, String> argumentsMap;
    private Model model;
    private TaskList tasks;

    /**
     * Resets the fields for each test case.
     */
    void resetFields() {
        model = new Model();
        argumentsMap = new HashMap<>();
        tasks = (TaskList) model.getList(ListType.TASK_LIST);
    }

    @Test
    void execute_validDate_setsNewDate() throws DukeException {
        resetFields();

        tasks.addTodo(TEST_DESCRIPTION);
        argumentsMap.put("date", "13-05-2020");
        Command dateCommand = new DateCommand(1, argumentsMap);

        dateCommand.execute(model);
        assertEquals("13 May 2020", tasks.get(0).getDateString(Task.DATETIME_PRINT_FORMAT));
    }

    @Test
    void execute_invalidDate_throwsException() {
        resetFields();

        tasks.addTodo(TEST_DESCRIPTION);
        argumentsMap.put("date", "xx-yy-zzzz");
        Command dateCommand = new DateCommand(1, argumentsMap);

        assertThrows(DukeException.class, () -> dateCommand.execute(model));
    }

    @Test
    void execute_noDate_throwsException() {
        resetFields();

        tasks.addTodo(TEST_DESCRIPTION);
        Command dateCommand = new DateCommand(1, argumentsMap);

        assertThrows(DukeException.class, () -> dateCommand.execute(model));
    }
}