package seedu.duke.todo;

import seedu.duke.lists.QuotesifyList;

import java.util.ArrayList;

public class ToDoList extends QuotesifyList<ToDo> {
    private ArrayList<ToDo> todos = super.getList();

    public ToDoList(){
        super(new ArrayList<>());
    }

    public ToDoList(ArrayList<ToDo> toDos){
        super(toDos);
    }

    public void add(ToDo newToDo) {
        todos.add(newToDo);
    }

    public ToDo find(int taskNum) {
        if(taskNum <= todos.size()) {
            return todos.get(taskNum);
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(int index) {
        todos.remove(index);
    }

    @Override
    public String toString() {
        String toDosToReturn = "";

        for (ToDo toDo : todos) {
            toDosToReturn += toDo.toString() + System.lineSeparator();
        }

        return toDosToReturn;
    }
}
