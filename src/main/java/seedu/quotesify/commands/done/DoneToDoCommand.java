package seedu.quotesify.commands.done;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

public class DoneToDoCommand extends DoneCommand {
    public DoneToDoCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        ToDoList toDos = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        int index = computeToDoIndex(information.trim());
        doneToDo(toDos,index,ui);
    }

    private void doneToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo targetTask = toDos.find(index);
        if (targetTask != null) {
            targetTask.setDone(true);
            ui.printDoneToDo(targetTask);
        } else {
            System.out.println(ERROR_TODO_NOT_FOUND);
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
