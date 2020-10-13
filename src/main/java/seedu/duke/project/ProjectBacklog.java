package seedu.duke.project;

import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ProjectBacklog {

    public ArrayList<Task> backlogTasks;
    int size;

    public ProjectBacklog() {
        backlogTasks = new ArrayList<>(100);
        size = 0;
    }

    public void addTask(Task task) {
        backlogTasks.add(task);
        task.setId(backlogTasks.size() - 1);
        size++;
    }

    public int size() {
        return size;
    }

    public Task getTask(int i) {
        return backlogTasks.get(i);
    }

    public Task removeTask(int i) {
        Task removedObj = backlogTasks.remove(i);
        //update the rest of the ID since array has shifted
        for (int j = i; j < backlogTasks.size(); j++) {
            Task task = backlogTasks.get(j);
            task.setId(j);
        }
        return removedObj;
    }

    public void viewTask(String id, Ui ui) {
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
    //    public void viewTask(String id, Ui ui) {
    //        Task task = null;
    //        try {
    //            int backlogId = Integer.parseInt(id) - 1;
    //            if (backlogId < size) {
    //                task = backlogTasks.get(backlogId);
    //                ui.displayTask(task);
    //            } else {
    //                ui.displayInvalidId();
    //            }
    //        } catch (NumberFormatException e) {
    //            ui.printError("Task id is not an integer.");
    //        }
    //    }
    //
    //    public void deleteTask(List<String> taskId, Ui ui) {
    //
    //        for (String id : taskId) {
    //            try {
    //                int backlogId = Integer.parseInt(id) - 1;
    //                if (backlogId < size) {
    //                    ui.printTaskRemoved(backlogTasks.get(backlogId));
    //                    backlogTasks.remove(backlogId);
    //                } else {
    //                    ui.displayInvalidId();
    //                }
    //            } catch (NumberFormatException e) {
    //                ui.printError("Task id is not an integer.");
    //            }
    //        }
    //    }
}
