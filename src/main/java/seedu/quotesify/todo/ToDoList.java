package seedu.quotesify.todo;

import org.json.simple.JSONArray;

import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;
import java.util.Comparator;

//@@author lunzard

/**
 * Represents a TodoList of tasks.
 */
public class ToDoList extends QuotesifyList<ToDo> {
    private ArrayList<ToDo> todos = super.getList();

    /**
     * Constructor for empty ToDoList.
     */
    public ToDoList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor for ToDoList with tasks.
     * @param toDos Tasks to be added into BookList.
     */
    public ToDoList(ArrayList<ToDo> toDos) {
        super(toDos);
    }

    /**
     * Adds a new task to the ToDoList.
     *
     * @param newToDo New task to be added to the ToDoList.
     */
    public void add(ToDo newToDo) {
        todos.add(newToDo);
    }

    /**
     * Finds the task with the selected task number in the ToDoList.
     *
     * @param taskNum selected task number in the ToDoList.
     * @return Task with the selected task number.
     */
    public ToDo find(int taskNum) {
        int indexNum = taskNum - 1;
        if (taskNum <= todos.size() && indexNum >= 0 && todos.size() > 0) {
            return todos.get(indexNum);
        } else {
            return null;
        }
    }

    /**
     * Returns the number of tasks the ToDoList has.
     *
     * @return Number of tasks in the ToDoList.
     */
    public int getSize() {
        return todos.size();
    }

    /**
     * Deletes a task from the ToDoList, specified by its task number.
     *
     * @param taskNum Task number in the list.
     */
    @Override
    public void delete(int taskNum) {
        int indexNum = taskNum - 1;
        todos.remove(indexNum);
    }

    /**
     * Sort the ToDoList in ascending order of their deadline.
     */
    public void sortByDate() {
        todos.sort(Comparator.comparing(todo -> todo.getFormattedDeadline()));
    }

    /**
     * Converts the ToDoList into a String.
     *
     * @return String of tasks in ToDoList.
     */
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

    /**
     * Converts the ToDoList into a JSONArray.
     *
     * @return JSONArray of ToDoList.
     */
    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (ToDo todo : todos) {
            list.add(todo.toJson());
        }
        return list;
    }
}
