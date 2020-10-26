package seedu.duke.command.sprint;

import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddSprintTaskCommand extends SprintCommand {
    private static final Logger LOGGER = Logger.getLogger(AddSprintTaskCommand.class.getName());

    public AddSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
        LOGGER.setLevel(Level.WARNING);
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
            this.sprintOwner.addSprintTask(taskId);

            //Update Task
            Task addedTask = this.projOwner.getProjectBacklog().getTask(taskId);
            addedTask.allocateToSprint(this.sprintOwner.getId());

            //Output to user
            Ui.showToUser(projOwner.getProjectBacklog().getTask(taskId).getTitle() + " added to sprint "
                    + this.sprintOwner.getId()
                    + ".\n");

            //Log
            LOGGER.log(Level.INFO, "AddSprintTaskCommand executed successfully"
                    + System.lineSeparator()
                    + projOwner.getProjectBacklog().getTask(taskId).getTitle());
        }
    }



}
