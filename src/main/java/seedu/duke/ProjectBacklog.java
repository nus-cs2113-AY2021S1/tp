package seedu.duke;

import ui.Ui;

import java.util.ArrayList;

public class ProjectBacklog {

    ArrayList<Task> backlogTasks;

    public ProjectBacklog() {
        backlogTasks = new ArrayList<>(100);
    }

    protected void addTask(Task task) {
        backlogTasks.add(task);
    }

    protected int size() {
        return backlogTasks.size();
    }

    public Task getTask(int i) {
        return backlogTasks.get(i);
    }

    public void viewTask(String id, Ui ui) {
        Task task = null;
        try {
            int backlogId = Integer.parseInt(id);
            task = backlogTasks.get(backlogId - 1);
            ui.displayTask(task);
        } catch (NumberFormatException e) {
            ui.printError("Invalid task id.");
        }
    }
}
