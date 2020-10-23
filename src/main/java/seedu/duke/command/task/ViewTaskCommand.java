package seedu.duke.command.task;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewTaskCommand extends TaskCommand {
    private final ProjectList projectListManager;
    private Project proj;

    public ViewTaskCommand(Hashtable<String, String> parameters, ProjectList projectListManager) {
        super(parameters);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        assert !projectListManager.isEmpty() : "No project found!\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        try {
            Project proj = projectListManager.getProject();
            if (parameters.isEmpty()) {
                Ui.showError("Missing parameters.");
            }
            Ui.showToUserLn("The details of the tasks are as follows: ");
            for (int i = 0; i < parameters.size(); i++) {
                Task task;
                int backlogId = Integer.parseInt(parameters.get(Integer.toString(i)));
                //if (backlogId <= proj.getProjectBacklog().backlogTasks.size()) {
                if (backlogId <= proj.getProjectBacklog().getNextId()) {
                    task = proj.getProjectBacklog().getTask(backlogId);
                    Ui.showToUserLn(task.toString());
                } else {
                    Ui.showError(Messages.MESSAGE_INVALID_ID);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        } catch (NullPointerException e) {
            Ui.showError("The requested task has been removed from the project.");
        } catch (NumberFormatException e) {
            Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
        }
    }
}
