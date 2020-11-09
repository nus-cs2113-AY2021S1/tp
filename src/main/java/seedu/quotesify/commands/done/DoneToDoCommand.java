package seedu.quotesify.commands.done;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

//@@author lunzard

/**
 * Represents the command to mark tasks as complete in the ToDoList.
 */
public class DoneToDoCommand extends DoneCommand {
    /**
     * Constructor for the DoneToDo Command.
     *
     * @param arguments Inputs by the user.
     */
    public DoneToDoCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DoneToDo Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        int index = computeToDoIndex(information.trim());
        doneToDo(toDos,index,ui);
    }

    /**
     * Find the selected task and mark it as complete.
     *
     * @param toDos ToDOList in Quotesify.
     * @param index  Index of the selected task in the ToDoList.
     * @param ui Ui of the program.
     */
    private void doneToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo targetTask = toDos.find(index);
        if (targetTask != null) {
            targetTask.setDone(true);
            ui.printDoneToDo(targetTask);
        } else {
            System.out.println(ERROR_TODO_NOT_FOUND);
        }
    }

    /**
     * Convert the task index number from String to int.
     * Negative task index number will be saved as -1 for further handling.
     *
     * @param information task index number in String.
     * @return task index number in int.
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
