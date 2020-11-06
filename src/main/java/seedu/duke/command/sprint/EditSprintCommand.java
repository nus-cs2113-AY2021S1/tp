package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class EditSprintCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private String sprintGoal;

    /**
     * Creates a new EditSprint command with arguments.
     */
    public EditSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
    }

    /**
     * Abstract method that execute the command.
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
        } catch (DukeException e) {
            e.printExceptionMessage();
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
}
