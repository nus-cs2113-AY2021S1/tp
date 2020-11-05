package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TASK_ID;

public class ChangeTaskPriorityCommand extends Command {
    private final ProjectManager projectListManager;
    private Project proj;

    public ChangeTaskPriorityCommand(Hashtable<String,String> parameters, ProjectManager projectListManager) {
        super(parameters, true);
        this.projectListManager = projectListManager;
    }

    public void execute() {

        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        Task task;
        int id;
        String priority;

        id = Integer.parseInt(parameters.get(TASK_ID));
        priority = parameters.get(PRIORITY).trim().toUpperCase();

        Project proj = projectListManager.getSelectedProject();
        try {
            task = proj.getTaskList().getTask(id);
            if (!proj.getTaskList().checkValidPriority(priority)) {
                Ui.showError("Invalid priority!");
                return;
            }
            task.setPriority(priority);
            Ui.showToUserLn("The task " + task.getTitle() + " has its priority changed to:");
            Ui.showToUserLn("\t" + task.getPriority());
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("Task ID entered is out of bounds!");
        } catch (IllegalArgumentException e) {
            Ui.showError("Priority entered is invalid!");
        } catch (NullPointerException e) {
            Ui.showError("The task does not exist or has been deleted.");
        }

    }
}
