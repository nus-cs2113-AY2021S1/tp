package seedu.duke.commands;

import seedu.duke.lists.ListManager;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

public class ToDoCommand extends Command{
    private String information;

    public ToDoCommand(String arguments) {
        this.information = arguments;
    }

    @Override
    public void execute(TextUi ui, ListManager listManager) {
        ToDoList toDos = (ToDoList) listManager.getList(ListManager.TODO_LIST);
        ToDo newToDo = addToDo(toDos);
        ui.printAddToDo(newToDo);
    }

    private ToDo addToDo(ToDoList toDos) {
        String[] taskNameAndDeadline = information.split("/by", 2);
        String taskName = taskNameAndDeadline[0].trim();
        String deadline = taskNameAndDeadline[1].trim();
        ToDo newToDo = new ToDo(taskName,deadline);
        toDos.add(newToDo);

        return newToDo;
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
