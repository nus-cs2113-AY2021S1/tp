package seedu.duke.model.task;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.model.project.Project;
import seedu.duke.storage.JsonableObject;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TaskManager implements JsonableObject {

    private Project proj;
    public ArrayList<Task> backlogTasks;
    int nextId;

    public TaskManager() {
    }

    public TaskManager(Project proj) {
        this.proj = proj;
        backlogTasks = new ArrayList<>(100);
        nextId = 1;
    }

    public boolean isEmpty() {
        return backlogTasks.isEmpty();
    }

    public int size() {
        return backlogTasks.size();
    }

    public int getNextId() {
        return nextId;
    }
    
    public void setProj(Project proj) {
        this.proj = proj;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }
    
    public void addTask(String title, String description, String priority) {
        int newTaskId = nextId++;
        backlogTasks.add(new Task(newTaskId, title, description, priority));
    }

    public boolean checkValidPriority(String input) {
        for (Priority priority : Priority.values()) {
            if (priority.name().equals(input)) {
                return true;
            }
        }
        return false;
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
                ArrayList<Integer> allocatedSprint = task.getSprintList();
                for (Integer sprintId : allocatedSprint) {
                    proj.getSprintList().getSprint(sprintId).removeSprintTask(taskId);
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
        jsonBacklog.put("owner", proj.getProjectID());
        jsonBacklog.put("backlogTasks", jsonTasks);
        jsonBacklog.put("nextId", nextId);
        jsonBacklog.toJson(writer);
    }

    public void fromJson(JsonObject jsonObject, Project project) {
        proj = project;
        assert project != null && proj.getProjectID() == JsonableObject.parseInt(jsonObject, "owner") 
                : "Project Id does not corresponds to the project object which this backlog is stored under.";
        nextId = JsonableObject.parseInt(jsonObject, "nextId");
        proj = project;
        backlogTasks = new ArrayList<>();
        JsonArray jsonTaskList = (JsonArray) jsonObject.get("backlogTasks");

        for (Object o : jsonTaskList) {
            Task task = new Task();
            task.fromJson((JsonObject) o);
            backlogTasks.add(task);
        }
    }

    @Override
    public void fromJson(JsonObject jsonObj) {
        fromJson(jsonObj, null);
    }
}
