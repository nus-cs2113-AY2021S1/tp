package seedu.duke.command.task;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class PriorityViewCommand extends TaskCommand {
    private final ProjectManager projectListManager;
    private Project proj;

    public PriorityViewCommand(Hashtable<String, String> parameters, ProjectManager projectListManager) {
        super(parameters, false);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        assert !projectListManager.isEmpty() : "No project found!\n";
        if (projectListManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        try {
            Project proj = projectListManager.getSelectedProject();
            ArrayList<Task> tasks = proj.getBacklog().getTaskList();
            ArrayList<Task> highPriorityTasks = new ArrayList<>();
            ArrayList<Task> mediumPriorityTasks = new ArrayList<>();
            ArrayList<Task> lowPriorityTasks = new ArrayList<>();

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

            Ui.showToUserLn("The details of the tasks, in descending priority, are as follows: ");

            for (Task task : highPriorityTasks) {
                Ui.showToUser(task.toString());
            }
            for (Task task : mediumPriorityTasks) {
                Ui.showToUser(task.toString());
            }
            for (Task task :lowPriorityTasks) {
                Ui.showToUser(task.toString());
            }

        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }
}
