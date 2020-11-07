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

    /**
     * Checks whether the task list is empty. Returns true if it is empty.
     * @return whether task list empty.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Returns the ID after the ID of the latest task.
     * @return next ID.
     */
    public int getNextId() {
        return nextId;
    }

    /**
     * Sets the project where task operations will handle.
     * @param proj the project to be subject to task operations
     */
    public void setProj(Project proj) {
        this.proj = proj;
    }

    /**
     * Adds a task to the task list.
     * @param title the title of the task.
     * @param description the description of the task.
     * @param priority the priority of the task.
     */
    public void addTask(String title, String description, String priority) {
        int newTaskId = nextId++;
        taskList.add(new Task(newTaskId, title, description, priority));
    }

    /**
     * Checks whether the priority entered is valid.
     * @param input the user's input.
     * @return true if the priority is valid, and false otherwise.
     */
    public boolean checkValidPriority(String input) {
        for (Priority priority : Priority.values()) {
            if (priority.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares the user input with the prescribed priorities.
     * @param name the prescribed priorities.
     * @param input the user input.
     * @return true if the user input matches, and false otherwise.
     */
    public boolean compareString(String name, String input) {
        if (name.equals(input)) {
            return true;
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
                cleanupSprint(task);
                taskList.remove(task);
                return;
            }
        }
    }

    /**
     * Cleans up the sprint list when a task is removed.
     * @param task the removed task.
     */
    public void cleanupSprint(Task task) {
        ArrayList<Integer> allocatedSprint = task.getSprintList();
        for (Integer sprintId : allocatedSprint) {
            proj.getSprintList().getSprint(sprintId).removeSprintTask(task.getId());
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Checks whether the task of ID exist.
     * @param id the ID of the task to be checked.
     * @return true if the task exists, and false otherwise.
     */
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
