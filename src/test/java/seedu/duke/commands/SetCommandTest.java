package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.item.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetCommandTest extends CommandTest {

    @Test
    void execute_validPriority_setsNewPriority() throws DukeException {
        resetFields();

        int initialPriority = 0;
        int newPriority = 3;
        argumentsMap.put("p", Integer.toString(newPriority));
        Command setCommand = new SetCommand(1, argumentsMap);

        tasks.addItem(new Task("test description"));
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

        tasks.addItem(new Task("test description"));
        assertThrows(DukeException.class, () -> {
            setCommand.execute(model);
        });
    }

    @Test
    void execute_invalidPriority_throwsException() {
        resetFields();

        String newPriority = "a";
        argumentsMap.put("p", newPriority);
        Command setCommand = new SetCommand(1, argumentsMap);

        tasks.addItem(new Task("test description"));
        assertThrows(DukeException.class, () -> {
            setCommand.execute(model);
        });
    }

}
