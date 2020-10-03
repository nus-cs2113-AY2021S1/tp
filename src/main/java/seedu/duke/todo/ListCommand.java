package seedu.duke.todo;

public class ListCommand {

    public void execute(ToDoList toDos) {
        toDos.listTodos();
    }
}
