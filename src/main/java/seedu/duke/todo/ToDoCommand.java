package seedu.duke.todo;

public class ToDoCommand {
    private ToDo toDo;

    public ToDoCommand(String taskName, String deadline) {
        toDo = new ToDo(taskName,deadline);
    }

    public void execute(ToDoList toDos) {
        toDos.add(toDo);
    }
}
