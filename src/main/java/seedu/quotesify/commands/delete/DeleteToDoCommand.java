package seedu.quotesify.commands.delete;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

public class DeleteToDoCommand extends DeleteCommand{
    public DeleteToDoCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        int index = computeToDoIndex(information.trim());
        deleteToDo(toDos, index, ui);
    }

    private void deleteToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo toDoToBeDeleted = toDos.find(index);
        if (toDoToBeDeleted != null) {
            toDos.delete(index);
            ui.printDeleteToDo(toDoToBeDeleted);
        } else {
            ui.printErrorMessage(ERROR_TODO_NOT_FOUND);
        }
    }

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
