package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;

public class AddTaskCommand extends TaskCommand {
    private final ProjectManager projectManager;

    public AddTaskCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {
        assert !projectManager.isEmpty() : "No project\n";
        if (projectManager.isEmpty()) {
            handleMissingProject("No project : add task.");
            return;
        }
        String title;
        boolean titleExists;
        title = parameters.get(TITLE);
        titleExists = checkTitleExist(title);
        if (titleExists) {
            handleDuplicateTitle("Duplicate task : adding task.");
            return;
        }

        String description;
        String priority;
        description = parameters.get(DESCRIPTION);
        priority = parameters.get(PRIORITY).toUpperCase();
        Project proj = projectManager.getSelectedProject();
        if (!proj.getBacklog().checkValidPriority(priority)) {
            handleInvalidPriority("Syntax error : add task.");
            return;
        }
        proj.getBacklog().addTask(title, description, priority);
        displayToUser(proj);
    }

    /**
     * Displays the added task to the user.
     * @param proj the project the task belongs to.
     */
    private void displayToUser(Project proj) {
        Task addedTask = proj.getBacklog().getTask(proj.getBacklog().getNextId() - 1);
        Ui.showToUserLn("Task successfully created.");
        Ui.showToUserLn(addedTask.toString());
        ScrumLogger.LOGGER.info(String.format("Added backlog task, ID : %d",
                addedTask.getId()));
    }

    /**
     * Checks whether the title already exists.
     * @param title the title of the task the user wishes to add.
     * @return whether the title already exists.
     */
    public boolean checkTitleExist(String title) {
        boolean titleAlreadyExist = false;
        if (projectManager.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= projectManager.getSelectedProject().getBacklog().size(); i++) {
            Task existingTask = projectManager.getSelectedProject().getBacklog().getTask(i);
            if (existingTask != null) {
                String existingTitle = existingTask.getTitle();
                titleAlreadyExist |= existingTitle.equals(title);
            }
        }
        return titleAlreadyExist;
    }




}



