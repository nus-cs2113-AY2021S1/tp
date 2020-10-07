package seedu.duke;

import ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class ProjectBacklog {

    ArrayList<Task> backlogTasks;
    int size;

    public ProjectBacklog() {
        backlogTasks = new ArrayList<>(100);
        size = 0;
    }

    protected void addTask(Task task) {
        size++;
        backlogTasks.add(task);
    }

    protected int size() {
        return size;
    }

    public Task getTask(int i) {
        return backlogTasks.get(i);
    }

    public void viewTask(String id, Ui ui) {
        Task task = null;
        try {
            int backlogId = Integer.parseInt(id) - 1;
            if (backlogId < size) {
                task = backlogTasks.get(backlogId);
                ui.displayTask(task);
            } else {
                ui.displayInvalidId();
            }
        } catch (NumberFormatException e) {
            ui.printError("Task id is not an integer.");
        }
    }

    public void deleteTask(List<String> taskId, Ui ui) {

        for (String id : taskId) {
            try {
                int backlogId = Integer.parseInt(id) - 1;
                if (backlogId < size) {
                    ui.printTaskRemoved(backlogTasks.get(backlogId));
                    backlogTasks.remove(backlogId);
                } else {
                    ui.displayInvalidId();
                }
            } catch (NumberFormatException e) {
                ui.printError("Task id is not an integer.");
            }
        }
    }
}
