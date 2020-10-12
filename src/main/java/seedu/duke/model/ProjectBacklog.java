package seedu.duke.model;

import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import java.util.List;

public class ProjectBacklog {

    ArrayList<Task> backlogTasks;
    int size;

    public ProjectBacklog() {
        backlogTasks = new ArrayList<>(100);
        size = 0;
    }

    public void addTask(Task task) {
        size++;
        backlogTasks.add(task);
    }

    protected int size() {
        return size;
    }

    public Task getTask(int i) {
        return backlogTasks.get(i);
    }

    public void viewTask(String id, TextUi ui) {
        Task task;
        try {
            int backlogId = Integer.parseInt(id) - 1;
            if (backlogId < size) {
                task = backlogTasks.get(backlogId);
                ui.showToUser(task.toString());
            } else {
                ui.showToUser("The following task id doesn't exist in backlog.\n Please enter a valid id.");
            }
        } catch (NumberFormatException e) {
            ui.showToUser("Task id is not an integer.");
        }
    }

    public void deleteTask(List<String> taskId, TextUi ui) {

        for (String id : taskId) {
            try {
                int backlogId = Integer.parseInt(id) - 1;
                if (backlogId < size) {
                    ui.showToUser("The corresponding task "
                            + backlogTasks.get(backlogId).toString()
                            + " has been removed.");
                    backlogTasks.remove(backlogId);
                } else {
                    ui.showToUser("Task id is not an integer.");
                }
            } catch (NumberFormatException e) {
                ui.showToUser("Task id is not an integer.");
            }
        }
    }
}
