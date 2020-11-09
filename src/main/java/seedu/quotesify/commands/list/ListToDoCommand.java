package seedu.quotesify.commands.list;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

//@@author lunzard

/**
 * Represents the command to list all tasks from Quotesify's ToDoList.
 */
public class ListToDoCommand extends ListCommand {
    /**
     * Constructor for the ListToDo Command.
     *
     * @param arguments Inputs by the user.
     */
    public ListToDoCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the ListToDo Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        ToDoList toDoList = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        listToDos(toDoList,ui);
    }

    /**
     * print all tasks in the ToDoList.
     *
     * @param toDoList ToDoList in Quotesify.
     * @param ui Ui of the program.
     */
    private void listToDos(ToDoList toDoList, TextUi ui) {
        toDoList.sortByDate();
        ui.printAllToDos(toDoList);
    }
}
