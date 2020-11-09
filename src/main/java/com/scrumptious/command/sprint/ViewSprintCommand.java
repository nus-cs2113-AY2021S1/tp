package com.scrumptious.command.sprint;

import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

public class ViewSprintCommand extends SprintCommand {

    public ViewSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, false);
    }

    public void execute() {
        try {
            checkProjectExist(-1);
            chooseProject();
            checkSprintExist();
            chooseSprint();

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            Ui.showToUser(this.sprintOwner.toString());
            logExecution();
        } catch (ScrumptiousException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Viewed Sprint");
    }


}
