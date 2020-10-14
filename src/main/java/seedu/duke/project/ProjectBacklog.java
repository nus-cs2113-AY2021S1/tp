package seedu.duke.project;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class ProjectBacklog implements Jsonable {

    public ArrayList<Task> backlogTasks;
    int size;

    public ProjectBacklog() {
        backlogTasks = new ArrayList<>(100);
        size = 0;
    }

    public void addTask(String title, String description, String priority) {
        int newTaskId = size++;
        backlogTasks.add(new Task(newTaskId, title, description, priority));
    }

    public int size() {
        return size;
    }

    public Task getTask(int id) {
        for (Task task: backlogTasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
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
                Ui.showToUserLn(task.toString());
            } else {
                Ui.showToUserLn("The following task id doesn't exist in backlog.\n Please enter a valid id.");
            }
        } catch (NumberFormatException e) {
            Ui.showToUserLn("Task id is not an integer.");
        }
    }

    @Override
    public String toJson() {
        final StringWriter writeable = new StringWriter();
        try {
            this.toJson(writeable);
        } catch (IOException e) {
            System.out.println("[Error] Cannot convert this project to JSON");
            e.printStackTrace();
        }
        return writeable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonArray jTasks = new JsonArray(backlogTasks);
        jTasks.toJson(writer);
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
