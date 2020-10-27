package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Messages;
import seedu.duke.model.project.Project;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.Hashtable;

public class CreateSprintCommand extends SprintCommand {

    /**
     * Creates a new DELETE command with arguments.
     */
    public CreateSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
    }

    /**
     * Abstract method that execute the command.
     */
    public void execute() {
        chooseProject();
        Ui.showToUser(this.projOwner.toIdString());
        if (sprintList.size() == 0) {
            try {
                createFirstSprint(projOwner);
            } catch (DukeException e) {
                e.printExceptionMessage();
            }
        } else {
            createSubsequentSprint(projOwner);
        }
    }

    private void createFirstSprint(Project proj) throws DukeException {

        String sprintGoal = this.parameters.get("goal");
        LocalDate sprintStart = LocalDate.now();
        if (this.parameters.containsKey("start")) {
            sprintStart = DateTimeParser.parseDate(this.parameters.get("start"));
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);

        sprintList.addSprint(proj, sprintGoal, sprintStart, sprintEnd);

        LocalDate projEndDate = sprintStart.plusDays(proj.getProjectDuration() - 1);
        proj.setStartDate(sprintStart);
        proj.setEndDate(projEndDate);

        Ui.showToUserLn("\nProject will start along with the newly created sprint");
        Ui.showToUserLn("Project period: " + sprintStart + " to " + projEndDate);
        printCreatedSprint();

    }

    private void createSubsequentSprint(Project proj) {

        String sprintGoal = this.parameters.get("goal");
        Sprint prevSprint = sprintList.getSprint(sprintList.size());
        LocalDate sprintStart = prevSprint.getEndDate().plusDays(1);
        if (DateTimeParser.diff(proj.getEndDate(), sprintStart) >= 0) {
            Ui.showToUserLn("\nAll sprints are already created.");
            return;
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        sprintList.addSprint(proj, sprintGoal, sprintStart, sprintEnd);
        if (!this.parameters.containsKey("start")) {
            Ui.showToUserLn(Messages.MESSAGE_CREATE_SUB_SPRINT);
        }
        printCreatedSprint();
    }


    private void printCreatedSprint() {
        Sprint createdSprint = sprintList.getSprint(sprintList.size());
        Ui.showToUserLn(createdSprint.toString());
    }

}
