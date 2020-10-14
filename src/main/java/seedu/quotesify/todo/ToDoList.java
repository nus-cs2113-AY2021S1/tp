package seedu.quotesify.todo;

import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;

public class ToDoList extends QuotesifyList<ToDo> {
    private ArrayList<ToDo> todos = super.getList();

    public ToDoList() {
        super(new ArrayList<>());
    }

    public ToDoList(ArrayList<ToDo> toDos) {
        super(toDos);
    }

    public void add(ToDo newToDo) {
        todos.add(newToDo);
    }

    public ToDo find(int taskNum) {
        int indexNum = taskNum - 1;
        if (taskNum <= todos.size() && indexNum >= 0 && todos.size() > 0) {
            return todos.get(indexNum);
        } else {
            return null;
        }
    }

    public int getSize() {
        return todos.size();
    }

    @Override
    public void delete(int taskNum) {
        int indexNum = taskNum - 1;
        todos.remove(indexNum);
    }

    @Override
    public String toString() {
        String toDosToReturn = "";
        int taskNum = 0;

        for (ToDo toDo : todos) {
            taskNum++;
            toDosToReturn +=  taskNum + "." + toDo.toString() + System.lineSeparator();
        }

        return toDosToReturn;
    }
}
