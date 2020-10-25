package seedu.quotesify.commands.list;

import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

public class ListToDoCommand extends ListCommand{
    public ListToDoCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        ToDoList toDoList = (ToDoList) ListManager.getList(ListManager.TODO_LIST);
        listToDos(toDoList,ui);
    }

    private void listToDos(ToDoList toDoList, TextUi ui) {
        toDoList.sortByDate();
        ui.printAllToDos(toDoList);
    }
}
