package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Arrays;
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
        } catch (DukeException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Viewed Sprint");
    }


}
