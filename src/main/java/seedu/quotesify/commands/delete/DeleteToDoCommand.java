package seedu.quotesify.commands.delete;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

//@@author lunzard

/**
 * Represents the command to delete tasks from Quotesify's ToDolist.
 */
public class DeleteToDoCommand extends DeleteCommand {
    /**
     * Constructor for the DeleteToDo Command.
     *
     * @param arguments Inputs by the user.
     */
    public DeleteToDoCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DeleteToDo Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        int index = computeToDoIndex(information.trim());
        deleteToDo(toDos, index, ui);
    }

    /**
     * Delete tasks of selected index from the ToDoList.
     *
     * @param toDos ToDoList in Quotesify.
     * @param index  Index of the selected task in the ToDoList.
     * @param ui Ui of the program.
     */
    private void deleteToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo toDoToBeDeleted = toDos.find(index);
        if (toDoToBeDeleted != null) {
            toDos.delete(index);
            ui.printDeleteToDo(toDoToBeDeleted);
        } else {
            ui.printErrorMessage(ERROR_TODO_NOT_FOUND);
        }
    }

    /**
     * Convert the task number from String to int.
     * Negative task number will be saved as -1 for further handling.
     *
     * @param information Task number in String.
     * @return Task number in int.
     */
    private int computeToDoIndex(String information) {
        int index = 0;
        try {
            index = Integer.parseInt(information);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_TODO_NUM);
        }

        return index;
    }
}
