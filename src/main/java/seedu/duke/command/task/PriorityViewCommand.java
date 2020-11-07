package seedu.duke.command.task;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class PriorityViewCommand extends TaskCommand {
    private final ProjectManager projectListManager;

    public PriorityViewCommand(Hashtable<String, String> parameters, ProjectManager projectListManager) {
        super(parameters, false);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        assert !projectListManager.isEmpty() : "No project found!\n";
        if (projectListManager.isEmpty()) {
            handleMissingProject("No project : priority view.");
            return;
        }
        try {
            Project proj = projectListManager.getSelectedProject();
            ArrayList<Task> tasks = proj.getBacklog().getTaskList();
            ArrayList<Task> highPriorityTasks = new ArrayList<>();
            ArrayList<Task> mediumPriorityTasks = new ArrayList<>();
            ArrayList<Task> lowPriorityTasks = new ArrayList<>();

            arrangeTasks(tasks, highPriorityTasks, mediumPriorityTasks, lowPriorityTasks);
            Ui.showToUserLn("The details of the tasks, in descending priority, are as follows: ");
            printTasksByPriority(highPriorityTasks, mediumPriorityTasks, lowPriorityTasks);
            ScrumLogger.LOGGER.info("Viewed tasks by priority.");
        } catch (IndexOutOfBoundsException e) {
            handleMissingProject("No project : priority view.");
        }
    }

    /**
     * Arrange the the tasks in order of priority.
     * @param tasks the list of tasks to be arranged.
     * @param highPriorityTasks the list of high priority tasks after arrangement.
     * @param mediumPriorityTasks the list of medium priority tasks after arrangement.
     * @param lowPriorityTasks the list of low priority tasks after arrangement.
     */
    private void arrangeTasks(ArrayList<Task> tasks, ArrayList<Task> highPriorityTasks,
                              ArrayList<Task> mediumPriorityTasks, ArrayList<Task> lowPriorityTasks) {
        for (Task task: tasks) {
            if (task != null) {
                String priority = task.getPriority().substring(0, 1);
                if (priority.equals("H")) {
                    highPriorityTasks.add(task);
                }
                if (priority.equals("M")) {
                    mediumPriorityTasks.add(task);
                }
                if (priority.equals("L")) {
                    lowPriorityTasks.add(task);
                }
            }
        }
    }

    /**
     * Prints out the tasks in order of descending priority.
     * @param highPriorityTasks high priority tasks are printed first.
     * @param mediumPriorityTasks medium priority tasks are printed after high priority tasks.
     * @param lowPriorityTasks low priority tasks are printed last.
     */
    private void printTasksByPriority(ArrayList<Task> highPriorityTasks, ArrayList<Task> mediumPriorityTasks,
                                      ArrayList<Task> lowPriorityTasks) {
        for (Task task : highPriorityTasks) {
            Ui.showToUser(task.toString());
        }
        for (Task task : mediumPriorityTasks) {
            Ui.showToUser(task.toString());
        }
        for (Task task : lowPriorityTasks) {
            Ui.showToUser(task.toString());
        }
    }
}
