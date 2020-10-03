package seedu.duke.todo;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<ToDo> todos;

    public ToDoList(){
        todos = new ArrayList<ToDo>();
    }

    public void addToDo(ToDo toDo) {
        todos.add(toDo);
    }

    public ToDo findToDo(int taskNum) {
        if(taskNum <= todos.size()) {
            return todos.get(taskNum);
        }
        else {
            return null;
        }
    }

    public void removeTodo(int taskNum) {
        todos.remove(todos.get(taskNum));
    }

    public void listTodos(){
        for(int i = 0; i < todos.size(); i++){
            System.out.println();
        }
    }
}
