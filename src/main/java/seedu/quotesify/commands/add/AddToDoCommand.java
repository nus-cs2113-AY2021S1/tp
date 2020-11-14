package seedu.quotesify.commands.add;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

//@@author lunzard

/**
 * Represents the command to add tasks to Quotesify's ToDolist.
 */
public class AddToDoCommand extends AddCommand {

    /**
     * Constructor for the AddToDo Command.
     *
     * @param arguments Inputs by the user.
     */
    public AddToDoCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the AddToDo Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        ToDo newToDo = addToDo(toDos, ui);
        ui.printAddToDo(newToDo);
    }

    /**
     * Add new tasks to the ToDoList.
     *
     * @param toDos ToDoList in Quotesify.
     * @param ui Ui of the program.
     * @return Task that is added.
     */
    private ToDo addToDo(ToDoList toDos, TextUi ui) {
        String[] taskNameAndDeadline = information.split("/by", 2);
        ToDo newToDo = null;

        try {
            // if user did not provide deadline, let titleAndAuthor[1] be "not specified"
            if (taskNameAndDeadline.length == 1) {
                taskNameAndDeadline = new String[]{taskNameAndDeadline[0], "not specified"};
            }
            if (taskNameAndDeadline[0].trim().isEmpty()) {
                throw new QuotesifyException(ERROR_NO_TASK_NAME);
            }

            String taskName = taskNameAndDeadline[0].trim();
            assert !taskName.isEmpty() : "task name should not be empty";
            String deadline = taskNameAndDeadline[1].trim();
            assert !deadline.isEmpty() : "deadline should not be empty";
            newToDo = new ToDo(taskName, deadline);
            newToDo.updateDateFormat();
            toDos.add(newToDo);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.INFO, "add toDo to toDoList failed");
        }

        return newToDo;
    }
}
