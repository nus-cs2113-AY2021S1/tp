package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TASK_ID;

public class ChangeTaskPriorityCommand extends Command {
    private final ProjectList projectListManager;
    private Project proj;

    public ChangeTaskPriorityCommand(Hashtable<String,String> parameters, ProjectList projectListManager) {
        super(parameters);
        this.projectListManager = projectListManager;
    }

    public void execute() throws DukeException {

        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        Task task;
        int id;
        String priority;

        id = Integer.parseInt(parameters.get(TASK_ID));
        priority = parameters.get(PRIORITY).trim();

        Project proj = projectListManager.getProject();
        try {
            task = proj.getProjectBacklog().getTask(id);
            if (!proj.getProjectBacklog().checkValidPriority(priority)) {
                throw new DukeException("Invalid priority!");
            }
            task.setPriority(priority);
            Ui.showToUserLn("The task " + task.getTitle() + " has its priority changed to:");
            Ui.showToUserLn("\t" + task.getPriority());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task ID entered is out of bounds!");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Priority entered is invalid!");
        }

    }
}
