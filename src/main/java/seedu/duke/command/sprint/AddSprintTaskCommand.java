package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class AddSprintTaskCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private final ArrayList<Integer> taskIds;

    public AddSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
        this.taskIds = new ArrayList<>();
    }

    public void execute() {
        try {
            checkProjectExist(-1);
            chooseProject();
            checkSprintExist();
            prepareParameters();
            checkTasksExist(true);

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            addTasks();
        } catch (DukeException e) {
            e.printExceptionMessage();
        }
    }

    /**
     * Add tasks to Sprint.
     */
    private void addTasks() {
        for (int taskId : taskIds) {
            this.sprintOwner.addSprintTask(taskId);

            //Update Task
            Task addedTask = this.projOwner.getTaskList().getTask(taskId);
            addedTask.allocateToSprint(this.sprintOwner.getId());

            //Output to user
            Ui.showToUserLn(String.format("\t%s added to sprint %s.",
                    projOwner.getTaskList().getTask(taskId).getTitle(),
                    this.sprintOwner.getId()));
        }
    }

    /**
     * Prepare the parameters.
     */
    private void prepareParameters() throws DukeException {
        if (checkTagSpecified("task")) {
            chooseSprint();
            parseParamsToInt(parameters.get("task").split(" "));
        } else {
            selectCurrentSprint();
            parseParamsToInt(parameters.values().toArray(new String[0]));
        }
    }

    /**
     * Parse parameters into Integers.
     */
    private void parseParamsToInt(String[] taskIds) {
        for (String id : taskIds) {
            this.taskIds.add(Integer.parseInt(id));
        }
    }
}
