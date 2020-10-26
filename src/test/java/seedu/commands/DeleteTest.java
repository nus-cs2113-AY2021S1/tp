package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTest {

    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private Task t5;

    void setup() {
        try {
            t1 = new Task("meeting", "20-10-2020", null, null, null);
            t2 = new Task("meeting", "20-10-2020", null, null, "2");
            t3 = new Task("reading", "17-10-2020", null, null, "2");
            t4 = new Task("reading", "24-10-2020", "2200", "2300", null);
            t5 = new Task("random", "15-10-2020", null, null, "3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

    @Test
    public void deleteCommand_executeTest() throws InvalidTaskNumberException, InvalidCommandException {
        setup();
        TaskMap taskMap = new TaskMap();
        taskMap.addTask(t1);
        taskMap.addTask(t2);
        taskMap.addTask(t3);
        taskMap.addTask(t4);
        taskMap.addTask(t5);

        int id1 = t1.getTaskID();

        Delete delete = new Delete("delete " + Integer.toString(id1));
        CommandResult result = delete.execute(taskMap);
        assertEquals(4, taskMap.size());

    }

    @Test
    public void deleteCommand_invalidTaskNumber() throws InvalidTaskNumberException, InvalidCommandException {
        setup();
        TaskMap taskMap = new TaskMap();
        taskMap.addTask(t3);
        int wrongId3 = t3.getTaskID() + 1;

        Delete delete = new Delete("delete " + Integer.toString(wrongId3));
        assertThrows(InvalidTaskNumberException.class, () -> delete.execute(taskMap));

    }


}