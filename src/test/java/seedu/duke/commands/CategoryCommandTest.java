package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.ListType;
import seedu.duke.model.Model;
import seedu.duke.model.itemlist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryCommandTest extends CommandTest {

    @Test
    void execute_validCategory_setsNewCategory() throws DukeException {
        resetFields();

        String category = "test category";
        int index = 0;
        Model model = new Model();
        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);

        tasks.addTaskFromString("test description");
        Command categoryCommand = new CategoryCommand(index + 1, category);
        assertEquals(null, tasks.get(index).getCategory());
        categoryCommand.execute(model);
        assertEquals(category, tasks.get(index).getCategory());
    }

    @Test
    void execute_invalidIndex_throwsException() throws DukeException {
        resetFields();

        String category = "test category";
        int index = 0;
        Model model = new Model();
        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);

        tasks.addTaskFromString("test description");
        // index out of bound
        assertThrows(DukeException.class, () ->
                new CategoryCommand(index + 2, category).execute(model));

        // index = 0
        assertThrows(DukeException.class, () ->
                new CategoryCommand(index, category).execute(model));

        // index = -1
        assertThrows(DukeException.class, () ->
                new CategoryCommand(index - 1, category).execute(model));
    }
}
