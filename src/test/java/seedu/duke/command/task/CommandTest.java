package seedu.duke.command.task;

import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

public abstract class CommandTest {
    public CommandTest() {
    }

    public ProjectManager addTestProject() {
        ProjectManager projectList = new ProjectManager();
        projectList.addProject("title", "description", 3, 3);
        return projectList;
    }

    public Hashtable<String, String> addDefaultUserInput() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("title", "a");
        parameters.put("desc", "b");
        parameters.put("priority", "high");
        return parameters;
    }

    public Hashtable<String, String> addInvalidPriorityInput() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("title", "a");
        parameters.put("desc", "b");
        parameters.put("priority", "CRITICAL");
        return parameters;
    }
}
