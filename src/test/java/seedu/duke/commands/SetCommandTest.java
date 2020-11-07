package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.ListType;
import seedu.duke.model.Model;
import seedu.duke.model.itemlist.TaskList;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetCommandTest {
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
    void execute_validPriority_setsNewPriority() throws DukeException {
        resetFields();

        int initialPriority = 0;
        int newPriority = 3;
        argumentsMap.put("p", Integer.toString(newPriority));
        Command setCommand = new SetCommand(1, argumentsMap);

        tasks.addTodo(TEST_DESCRIPTION);
        assertEquals(initialPriority, tasks.get(0).getPriority());
        setCommand.execute(model);
        assertEquals(newPriority, tasks.get(0).getPriority());
    }

    @Test
    void execute_negativePriority_throwsException() {
        resetFields();

        int newPriority = -1;
        argumentsMap.put("p", Integer.toString(newPriority));
        Command setCommand = new SetCommand(1, argumentsMap);

        tasks.addTodo(TEST_DESCRIPTION);
        assertThrows(DukeException.class, () -> setCommand.execute(model));
    }

    @Test
    void execute_invalidPriority_throwsException() {
        resetFields();

        String newPriority = "a";
        argumentsMap.put("p", newPriority);
        Command setCommand = new SetCommand(1, argumentsMap);

        tasks.addTodo(TEST_DESCRIPTION);
        assertThrows(DukeException.class, () -> setCommand.execute(model));
    }
}
