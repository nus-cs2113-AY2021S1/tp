package seedu.duke.command.task;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewTaskCommand extends TaskCommand {
    private final ProjectManager projectListManager;

    public ViewTaskCommand(Hashtable<String, String> parameters, ProjectManager projectListManager) {
        super(parameters, false);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        assert !projectListManager.isEmpty() : "No project found!\n";
        if (projectListManager.isEmpty()) {
            handleMissingProject("No project : task view.");
            return;
        }
        handleViewTasks();
    }

    /**
     * Handler for viewing of tasks. Checks the prerequisites before calling the
     * print functions.
     */
    public void handleViewTasks() {
        try {
            Project proj = projectListManager.getSelectedProject();
            if (parameters.isEmpty()) {
                handleMissingParams("Syntax error : task view.");
                return;
            }
            printTasks(proj);
        } catch (IndexOutOfBoundsException e) {
            handleMissingProject("No project : task view.");
        } catch (NullPointerException e) {
            handleViewTaskDeletedId("Invalid task ID : task view.");
        } catch (NumberFormatException e) {
            handleMissingParams("Syntax error : task view.");
        }
    }

    /**
     * Prints all tasks requested by the user.
     * @param proj the project the tasks belong to.
     */
    private void printTasks(Project proj) {
        boolean firstTask = true;
        for (int i = 0; i < parameters.size(); i++) {
            int taskId = Integer.parseInt(parameters.get(Integer.toString(i)));
            if (taskId <= proj.getBacklog().getNextId() && taskId > 0) {
                firstTask = checkFirstTask(firstTask);
                printTask(proj, taskId);
            } else {
                handleBadId(taskId, "Invalid task ID : task view.");
            }
        }
    }

    /**
     * Checks whether the task to be printed is first on the list.
     * If the task is the first, the initial message is printed.
     * @param firstTask whether the task is first on the list.
     * @return false to denote that the next tasks are not the first.
     */
    private boolean checkFirstTask(boolean firstTask) {
        if (firstTask) {
            showInitialMessage();
        }
        return false;
    }

    /**
     * Prints individual tasks.
     * @param proj the project the task belongs to
     * @param taskId the ID of the task
     */
    private void printTask(Project proj, int taskId) {
        Task task = proj.getBacklog().getTask(taskId);
        ScrumLogger.LOGGER.info(String.format("Viewed task ID : %d", taskId));
        Ui.showToUserLn(task.toString());
    }

    /**
     * Displays the top message for viewing of tasks.
     */
    public static void showInitialMessage() {
        Ui.showToUserLn("The details of the tasks are as follows: ");
    }
}
