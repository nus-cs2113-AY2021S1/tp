package com.scrumptious.command.sprint;

import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

public class EditSprintCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private String sprintGoal;

    /**
     * Creates a new EditSprint command with arguments.
     * @param parameters - all parameters specified by user
     * @param projectList - the entire project manager that the program is working on
     */
    public EditSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
    }

    /**
     * Executes the command.
     */
    public void execute() {
        try {
            checkProjectExist(-1);
            chooseProject();
            checkSprintExist();
            chooseSprint();
            prepareParameters();

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            editSprint();
            logExecution();
        } catch (ScrumptiousException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
        }

    }

    /**
     * Prepare the parameters.
     */
    private void prepareParameters() {
        this.sprintGoal = this.parameters.get("goal");
    }

    /**
     * Update goal of selected Sprint.
     */
    private void editSprint() {
        printEditMessage();
        this.sprintOwner.setGoal(sprintGoal);
        Ui.showToUser(this.sprintOwner.toString());
    }

    /**
     * Print message if provided goal is the same as current goal.
     */
    private void printEditMessage() {
        if (this.sprintOwner.getGoal().equals(sprintGoal)) {
            Ui.showToUserLn("Provided goal is the same as current goal.");
        } else {
            Ui.showToUserLn("Goal updated.");
        }
    }

    /**
     * Add entry to logger that execution is successful.
     */
    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info(String.format("Edited goal for Sprint %d - New Goal: %s",
                this.sprintOwner.getId(), this.parameters.get("goal")));
    }
}
