package seedu.duke.command.task;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TASK_ID;

public class ChangeTaskPriorityCommand extends TaskCommand {
    private final ProjectManager projectListManager;

    public ChangeTaskPriorityCommand(Hashtable<String,String> parameters, ProjectManager projectListManager) {
        super(parameters, true);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            handleMissingProject("No project : edit task priority.");
            return;
        }
        int id;
        String priority;
        id = Integer.parseInt(parameters.get(TASK_ID));
        priority = parameters.get(PRIORITY).trim().toUpperCase();
        changePriority(id, priority);
    }

    /**
     * Checks prerequisites and changes the priority of the task.
     * @param id the ID of the task.
     * @param priority the new priority of the task.
     */
    private void changePriority(int id, String priority) {
        Project proj = projectListManager.getSelectedProject();
        try {
            Task task = proj.getBacklog().getTask(id);
            if (!proj.getBacklog().checkValidPriority(priority)) {
                handleInvalidPriority("Syntax error : edit task priority.");
                return;
            }
            task.setPriority(priority);
            displayToUser(task);
        } catch (IndexOutOfBoundsException e) {
            handleInvalidId(id, "Invalid task ID : edit task priority.");
        } catch (IllegalArgumentException e) {
            handleInvalidPriority("Syntax error : edit task priority.");
        } catch (NullPointerException e) {
            handleIncorrectId("Invalid task ID : edit task priority.");
        }
    }

    /**
     * Displays the modified task and its new priority to the user.
     * @param task the modified task.
     */
    private void displayToUser(Task task) {
        Ui.showToUserLn("The task " + task.getTitle() + " has its priority changed to:");
        Ui.showToUserLn("\t" + task.getPriority());
        ScrumLogger.LOGGER.info(String.format("Changed task priority, ID : %d, new priority : %s",
                task.getId(), task.getPriority()));
    }
}
