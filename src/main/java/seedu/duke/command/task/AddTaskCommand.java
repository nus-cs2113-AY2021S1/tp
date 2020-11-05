package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;

public class AddTaskCommand extends Command {
    private final ProjectManager projectManager;
    private Project proj;

    public AddTaskCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {
        assert !projectManager.isEmpty() : "No project\n";
        if (projectManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        String title;
        boolean titleExists;

        title = parameters.get(TITLE);
        titleExists = doesTitleExist(title);
        if (titleExists) {
            Ui.showError("The task title already exists! "
                    + "Please enter an alternative name.");
            return;
        }

        String description;
        String priority;
        description = parameters.get(DESCRIPTION);
        priority = parameters.get(PRIORITY).toUpperCase();

        Project proj = projectManager.getSelectedProject();
        if (!proj.getTaskList().checkValidPriority(priority)) {
            Ui.showError("Invalid priority!");
            return;
        }
        proj.getTaskList().addTask(title, description, priority);
        Task addedTask = proj.getTaskList().getTask(proj.getTaskList().getNextId() - 1);
        Ui.showToUserLn("Task successfully created.");
        Ui.showToUserLn(addedTask.toString());
    }

    public boolean doesTitleExist(String title) {
        boolean titleAlreadyExist = false;
        if (projectManager.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= projectManager.getSelectedProject().getTaskList().size(); i++) {
            Task existingTask = projectManager.getSelectedProject().getTaskList().getTask(i);
            String existingTitle = existingTask.getTitle();
            titleAlreadyExist |= compareTitle(existingTitle, title);
        }
        if (titleAlreadyExist) {
            return true;
        } else {
            return false;
        }
    }

    public boolean compareTitle(String existingTitle, String title) {
        if (existingTitle.equals(title)) {
            return true;
        }
        return false;
    }
}



