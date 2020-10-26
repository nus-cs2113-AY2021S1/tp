package seedu.duke.command.sprint;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.SprintManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class RemoveSprintTaskCommand extends SprintCommand {
    private SprintManager allSprint;
    private ProjectManager projectManager;
    private Project proj;

    public RemoveSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
    }

    public void execute() {
        chooseProject();
        Ui.showToUserLn(this.projOwner.toIdString());
        String[] taskIds = new String[0];
        if (parameters.containsKey("task")) {
            chooseSprint();
            taskIds = parameters.get("task").split(" ");
        } else if (parameters.containsKey("0")) {
            selectCurrentSprint();
            taskIds = parameters.values().toArray(new String[0]);
        }
        for (String id : taskIds) {
            int taskId = Integer.parseInt(id);

            //Add task to sprint
            this.sprintOwner.removeSprintTask(taskId);

            //Update Task
            Task removedTask = this.projOwner.getProjectBacklog().getTask(taskId);
            removedTask.removeFromSprint(this.sprintOwner.getId());

            //Output to user
            Ui.showToUser(projOwner.getProjectBacklog().getTask(taskId).getTitle() + " removed from sprint "
                    + this.sprintOwner.getId()
                    + ".\n");
        }
    }
}
