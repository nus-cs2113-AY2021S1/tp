package com.scrumptious.command.sprint;

import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.task.Task;
import com.scrumptious.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class RemoveSprintTaskCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private final ArrayList<Integer> taskIds;

    /**
     * Creates a new RemoveSprintTask command with arguments.
     * @param parameters - all parameters specified by user
     * @param projectList - the entire project manager that the program is working on
     */
    public RemoveSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
        this.taskIds = new ArrayList<>();
    }

    /**
     * Executes the command.
     */
    public void execute() {
        try {
            checkProjectExist(-1);
            chooseProject();
            checkSprintExist();
            prepareParameters();
            checkTasksExist(false);

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            removeTasks();
            logExecution();
        } catch (ScrumptiousException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
        }
    }

    /**
     * Prepare the parameters.
     */
    private void removeTasks() {
        for (int taskId : taskIds) {
            if (!this.sprintOwner.checkTaskExist(taskId)) {
                Ui.showToUserLn(String.format("\t%s is already removed from sprint %s.",
                        projOwner.getBacklog().getTask(taskId).getTitle(),
                        this.sprintOwner.getId()));
                continue;
            }

            this.sprintOwner.removeSprintTask(taskId);

            //Update Task
            Task removedTask = this.projOwner.getBacklog().getTask(taskId);
            removedTask.removeFromSprint(this.sprintOwner.getId());

            //Output to user
            Ui.showToUserLn(String.format("\t%s removed from sprint %s.",
                    projOwner.getBacklog().getTask(taskId).getTitle(),
                    this.sprintOwner.getId()));
        }
    }

    /**
     * Prepare the parameters.
     */
    private void prepareParameters() throws ScrumptiousException {
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

    /**
     * Add entry to logger that execution is successful.
     */
    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info(String.format("Removed task from Sprint - %s",
                Arrays.toString(this.taskIds.toArray())));
    }
}
