package seedu.duke.project;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class ProjectBacklog implements Jsonable {

    private Project proj;
    public ArrayList<Task> backlogTasks;
    int nextId;

    public ProjectBacklog() {
    }

    public ProjectBacklog(Project proj) {
        this.proj = proj;
        backlogTasks = new ArrayList<>(100);
        nextId = 1;
    }

    public int getNextId() {
        return nextId;
    }
    
    public void setProj(Project proj) {
        this.proj = proj;
    }

    public void setBacklogTasks(ArrayList<Task> backlogTasks) {
        this.backlogTasks = backlogTasks;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }
    
    public void addTask(String title, String description, String priority) {
        int newTaskId = nextId++;
        backlogTasks.add(new Task(newTaskId, title, description, priority));
    }

    public Task getTask(int id) {
        for (Task task : backlogTasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void removeTask(int taskId) {
        for (Task task : backlogTasks) {
            if (task.getId() == taskId) {
                ArrayList<Integer> allocatedSprint = task.getAllocatedSprints();
                for (Integer sprintId : allocatedSprint) {
                    proj.allSprints.getSprint(sprintId).removeSprintTask(taskId);
                }
                backlogTasks.remove(task);
                return;
            }
        }
    }

    public void viewTask(String id, Ui ui) {
        Task task;
        try {
            int backlogId = Integer.parseInt(id) - 1;
            if (backlogId < nextId) {
                task = backlogTasks.get(backlogId);
                Ui.showToUserLn(task.toString());
            } else {
                Ui.showToUserLn("The following task id doesn't exist in backlog.\n Please enter a valid id.");
            }
        } catch (NumberFormatException e) {
            Ui.showToUserLn("Task id is not an integer.");
        }
    }

    public boolean checkTaskExist(int id) {
        for (Task task : backlogTasks) {
            if (task.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder backlogString = new StringBuilder();
        backlogString.append("\n---------------------------- BACKLOG ----------------------------\n");
        for (Task task : backlogTasks) {
            backlogString.append(task.toSimplifiedString());
        }
        backlogString.append("-----------------------------------------------------------------\n");
        return backlogString.toString();
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
        final JsonObject jsonBacklog = new JsonObject();
        final JsonArray jsonTasks = new JsonArray(backlogTasks);
        jsonBacklog.put("backlogTasks", jsonTasks);
        jsonBacklog.put("nextId", nextId);
        jsonBacklog.toJson(writer);
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
