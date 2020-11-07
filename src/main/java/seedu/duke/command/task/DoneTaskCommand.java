package seedu.duke.command.task;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class DoneTaskCommand extends TaskCommand {
    private final ProjectManager projectListManager;

    public DoneTaskCommand(Hashtable<String,String> parameters, ProjectManager projectListManager) {
        super(parameters, true);
        this.projectListManager = projectListManager;
    }

    public void execute() {

        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            handleMissingProject("No project : task completion.");
            return;
        }
        Project proj = projectListManager.getSelectedProject();
        if (parameters.size() == 0) {
            handleMissingParams("Syntax error : task completion.");
            return;
        }
        for (int i = 0; i < parameters.size(); i++) {
            try {
                markTaskDone(proj, i);
            } catch (NumberFormatException e) {
                handleNonIntegerId("Syntax error : task completion.");
            } catch (NullPointerException e) {
                handleDeletedId("Invalid task ID : task completion.");
            }
        }
    }

    /**
     * Checks the user input and set task as done.
     * @param proj the project the task belongs to.
     * @param input the user input.
     */
    private void markTaskDone(Project proj, int input) {
        Task task;
        int taskId = Integer.parseInt(parameters.get(Integer.toString(input)));
        if (taskId <= 0) {
            handleNegativeId(taskId, "Syntax error : task completion.");
        } else if (taskId <= proj.getBacklog().getNextId()) {
            task = proj.getBacklog().getTask(taskId);
            task.setAsDone();
            displayToUser(task, taskId);
        } else {
            handleInvalidId(taskId, "Invalid task ID : task completion.");
        }
    }

    /**
     * Displays the completed task ID and title to the user.
     * @param task the completed task.
     * @param taskId the ID of the completed task.
     */
    private void displayToUser(Task task, int taskId) {
        Ui.showToUserLn("The task ID: " + task.getId()
                + " and title: " + task.getTitle() + " has been marked as done.");
        ScrumLogger.LOGGER.info(String.format("Set task as done, ID : %d",
                taskId));
    }
}

