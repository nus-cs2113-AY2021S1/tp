package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewSprintCommand extends SprintCommand {

    public ViewSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList);
    }

    public void execute() {
        try {
            checkProjectExist();
            chooseProject();
            checkSprintExist();
            chooseSprint();

            //Valid Command
            Ui.showToUserLn(this.projOwner.toIdString());
            Ui.showToUser(this.sprintOwner.toString());
        } catch (DukeException e) {
            e.printExceptionMessage();
        }
    }
}
