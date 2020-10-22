package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class DoneTaskCommand extends Command {
    private final ProjectList projectListManager;
    private Project proj;

    public DoneTaskCommand(Hashtable<String,String> parameters, ProjectList projectListManager) {
        super(parameters);
        this.projectListManager = projectListManager;
    }

    public void execute() {

        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        Project proj = projectListManager.getProject();
        for (int i = 0; i < parameters.size(); i++) {
            Task task;
            try {
                int backlogId = Integer.parseInt(parameters.get(Integer.toString(i)));
                if (backlogId <= proj.getProjectBacklog().backlogTasks.size()) {
                    task = proj.getProjectBacklog().getTask(backlogId);
                    task.setAsDone();
                    Ui.showToUserLn(task.getTitle() + " has been marked as done.");
                } else {
                    Ui.showError(Messages.MESSAGE_INVALID_ID);
                }
            } catch (NumberFormatException e) {
                Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
            }
        }
    }
}

