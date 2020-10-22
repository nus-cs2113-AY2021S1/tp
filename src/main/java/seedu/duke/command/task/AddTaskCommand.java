package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.model.task.ProjectBacklog;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;

public class AddTaskCommand extends Command {
    private final ProjectList projectListManager;
    private Project proj;

    public AddTaskCommand(Hashtable<String, String> parameters, ProjectList projectListManager) {
        super(parameters);
        this.projectListManager = projectListManager;
    }

    public void execute()
            throws DukeException {
        assert !projectListManager.isEmpty() : "No project\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        String title;
        String description;
        String priority;

        title = parameters.get(TITLE);
        description = parameters.get(DESCRIPTION);
        priority = parameters.get(PRIORITY);


        Project proj = projectListManager.getProject();
        if (!proj.getProjectBacklog().checkValidPriority(priority)) {
            throw new DukeException("Invalid priority!");
        }
        proj.getProjectBacklog().addTask(title, description, priority);
        Task addedTask = proj.getProjectBacklog().getTask(proj.getProjectBacklog().getNextId() - 1);
        Ui.showToUserLn("Task successfully created.");
        Ui.showToUserLn(addedTask.toString());
    }
}


