package com.scrumptious.command.task;

import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.task.Task;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

import static com.scrumptious.command.CommandSummary.PRIORITY;
import static com.scrumptious.command.CommandSummary.TASK_ID;

public class ChangeTaskPriorityCommand extends TaskCommand {
    private final ProjectManager projectListManager;

    public ChangeTaskPriorityCommand(Hashtable<String,String> parameters, ProjectManager projectListManager) {
        super(parameters, true);
        this.projectListManager = projectListManager;
    }

    public void execute() {
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
            assert !(task == null) : "Task is NULL\n";
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
