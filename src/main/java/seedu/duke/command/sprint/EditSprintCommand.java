package seedu.duke.command.sprint;

import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class EditSprintCommand extends SprintCommand {

    /**
     * Creates a new DELETE command with arguments.
     */
    public EditSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
    }

    /**
     * Abstract method that execute the command.
     */
    public void execute() {
        chooseProject();
        chooseSprint();
        String sprintGoal = this.parameters.get("goal");
        this.sprintOwner.setGoal(sprintGoal);
        //Output to user
        Ui.showToUserLn(this.projOwner.toIdString());
        Ui.showToUser(this.sprintOwner.toString());
    }
}
