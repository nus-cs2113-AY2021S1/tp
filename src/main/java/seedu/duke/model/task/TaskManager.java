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
    public ArrayList<Task> taskList;
    int nextId;

    public TaskManager() {
    }

    public TaskManager(Project proj) {
        this.proj = proj;
        taskList = new ArrayList<>(100);
        nextId = 1;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
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
        taskList.add(new Task(newTaskId, title, description, priority));
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
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void removeTask(int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                ArrayList<Integer> allocatedSprint = task.getSprintList();
                for (Integer sprintId : allocatedSprint) {
                    proj.getSprintList().getSprint(sprintId).removeSprintTask(taskId);
                }
                taskList.remove(task);
                return;
            }
        }
    }

    public void viewTask(String id, Ui ui) {
        Task task;
        try {
            int taskId = Integer.parseInt(id) - 1;
            if (taskId < nextId) {
                task = taskList.get(taskId);
                Ui.showToUserLn(task.toString());
            } else {
                Ui.showToUserLn("The following task id doesn't exist in backlog.\n Please enter a valid id.");
            }
        } catch (NumberFormatException e) {
            Ui.showToUserLn("Task id is not an integer.");
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public boolean checkTaskExist(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append(String.format("---------------------------- BACKLOG ----------------------------%n"));
        for (Task task : taskList) {
            taskString.append(task.toSimplifiedString());
        }
        taskString.append(String.format("-----------------------------------------------------------------%n"));
        return taskString.toString();
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
        final JsonObject jsonTaskManager = new JsonObject();
        final JsonArray jsonTasks = new JsonArray(taskList);
        jsonTaskManager.put("owner", proj.getProjectID());
        jsonTaskManager.put("backlogTasks", jsonTasks);
        jsonTaskManager.put("nextId", nextId);
        jsonTaskManager.toJson(writer);
    }

    public void fromJson(JsonObject jsonObject, Project project) {
        proj = project;
        assert project != null && proj.getProjectID() == JsonableObject.parseInt(jsonObject, "owner")
                : "Project Id does not corresponds to the project object which this backlog is stored under.";
        nextId = JsonableObject.parseInt(jsonObject, "nextId");
        proj = project;
        taskList = new ArrayList<>();
        JsonArray jsonTaskList = (JsonArray) jsonObject.get("backlogTasks");

        for (Object o : jsonTaskList) {
            Task task = new Task();
            task.fromJson((JsonObject) o);
            taskList.add(task);
        }
    }

    @Override
    public void fromJson(JsonObject jsonObj) {
        fromJson(jsonObj, null);
    }
}
