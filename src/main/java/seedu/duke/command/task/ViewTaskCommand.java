package seedu.duke.command.task;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewTaskCommand extends TaskCommand {
    private final ProjectManager projectListManager;
    private Project proj;

    public ViewTaskCommand(Hashtable<String, String> parameters, ProjectManager projectListManager) {
        super(parameters, false);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        boolean validTask = false;
        assert !projectListManager.isEmpty() : "No project found!\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        try {
            Project proj = projectListManager.getSelectedProject();
            if (parameters.isEmpty()) {
                Ui.showError("Missing parameters.");
                return;
            }

            for (int i = 0; i < parameters.size(); i++) {
                Task task;
                int backlogId = Integer.parseInt(parameters.get(Integer.toString(i)));
                //if (backlogId <= proj.getProjectBacklog().backlogTasks.size()) {
                //Change to getNextId because each task's ID does not decrease with deletion.
                if (backlogId <= proj.getProjectBacklog().getNextId()) {
                    if (!validTask) {
                        Ui.showToUserLn("The details of the tasks are as follows: ");
                        validTask = true;
                    }
                    task = proj.getProjectBacklog().getTask(backlogId);
                    Ui.showToUserLn(task.toString());
                } else {
                    Ui.showError(Messages.MESSAGE_INVALID_ID);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        } catch (NullPointerException e) {
            Ui.showError("The task does not exist or has been deleted.");
        } catch (NumberFormatException e) {
            Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
        }
    }
}
