package seedu.duke.command.task;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class DeleteTaskCommand extends TaskCommand {
    private final ProjectManager projectListManager;

    public DeleteTaskCommand(Hashtable<String,String> parameters, ProjectManager projectListManager) {
        super(parameters, true);
        this.projectListManager = projectListManager;
    }

    public void execute() {

        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            handleMissingProject("No project : task deletion.");
            return;
        }
        Project proj = projectListManager.getSelectedProject();
        if (parameters.isEmpty()) {
            handleMissingParams("Syntax error : task deletion.");
        }
        for (int i = 0; i < parameters.size(); i++) {
            try {
                deleteTask(proj, i);
            } catch (NumberFormatException e) {
                handleNonIntegerId("Syntax error : task deletion.");
            }
        }
    }

    /**
     * Handles the deletion of the specified task.
     * @param proj the project the task belongs to.
     * @param input the user's input.
     */
    private void deleteTask(Project proj, int input) {
        int taskId = Integer.parseInt(parameters.get(Integer.toString(input)));
        if (taskId <= 0) {
            handleNegativeId(taskId, "Syntax error : task deletion.");
        } else if (proj.getBacklog().checkTaskExist(taskId)) {
            Task task = proj.getBacklog().getTask(taskId);
            displayToUser(taskId, task);
            proj.getBacklog().removeTask(taskId);
            ArrayList<Sprint> allSprints = proj.getSprintList().getSprintList();
            cleanupSprint(taskId, allSprints);
        } else {
            handleInvalidId(taskId, "Invalid task ID : task deletion.");
        }
    }

    /**
     * Remove traces of deleted task from all sprints.
     * @param taskId the ID of the to-be-deleted task.
     * @param allSprints all sprints.
     */
    private void cleanupSprint(int taskId, ArrayList<Sprint> allSprints) {
        for (Sprint sprint : allSprints) {
            if (sprint.checkTaskExist(taskId)) {
                sprint.removeSprintTask(taskId);
            }
        }
    }

    /**
     * Displays the removed task title to the user.
     * @param taskId the ID of the to-be-deleted task.
     * @param task the to-be-deleted task.
     */
    private void displayToUser(int taskId, Task task) {
        Ui.showToUserLn("The corresponding task "
                + task.getTitle()
                + " has been removed from project.");
        ScrumLogger.LOGGER.info(String.format("Deleted task, ID : %d",
                taskId));
    }
}
