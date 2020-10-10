package seedu.duke.commands;

import seedu.duke.lists.ListManager;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

public class DoneCommand extends Command{
    private String type;
    private String information;

    public DoneCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_TODO:
            ToDoList toDos = (ToDoList) listManager.getList(ListManager.TODO_LIST);
            int index = computeToDoIndex(information.trim());
            doneToDo(toDos,index,ui);
            break;
        default:
            break;
        }
    }

    private void doneToDo(ToDoList toDos, int index, TextUi ui) {
        ToDo targetTask = toDos.find(index);
        if(targetTask != null) {
            targetTask.setDone(true);
            ui.printDoneToDo(targetTask);
        }
        else {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
