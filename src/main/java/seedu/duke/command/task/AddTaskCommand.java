package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;

public class AddTaskCommand extends Command {
    private final ProjectManager projectListManager;
    private Project proj;

    public AddTaskCommand(Hashtable<String, String> parameters, ProjectManager projectListManager) {
        super(parameters, true);
        this.projectListManager = projectListManager;
    }

    public void execute() {
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
        priority = parameters.get(PRIORITY).toUpperCase();


        Project proj = projectListManager.getSelectedProject();
        if (!proj.getProjectBacklog().checkValidPriority(priority)) {
            Ui.showError("Invalid priority!");
            return;
        }
        proj.getProjectBacklog().addTask(title, description, priority);
        Task addedTask = proj.getProjectBacklog().getTask(proj.getProjectBacklog().getNextId() - 1);
        Ui.showToUserLn("Task successfully created.");
        Ui.showToUserLn(addedTask.toString());
    }
}


