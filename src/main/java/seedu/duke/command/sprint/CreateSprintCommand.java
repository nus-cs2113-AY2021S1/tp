package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Messages;
import seedu.duke.project.Project;
import seedu.duke.sprint.Sprint;
import seedu.duke.sprint.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class CreateSprintCommand extends SprintCommand {
    private SprintList allSprint;
    private ArrayList<Project> projectList;
    private Project proj;

    /**
     * Creates a new DELETE command with arguments.
     */
    public CreateSprintCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList) {
        super(parameters);
        this.projectList = projectList;
    }

    /**
     * Abstract method that execute the command.
     */
    public void execute() {
        assert !projectList.isEmpty() : "No project\n";
        if (projectList.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }
        proj = projectList.get(0);
        if (validateParams()) {
            allSprint = proj.getAllSprints();
            if (allSprint.size() == 0) {
                try {
                    createFirstSprint(proj);
                } catch (DukeException e) {
                    e.printExceptionMessage();
                }
            } else {
                createSubsequentSprint(proj);
            }
        } else {
            Ui.showError("Missing goal for this sprint.");
        }
    }

    private void createFirstSprint(Project proj) throws DukeException {

        LocalDate sprintStart = LocalDate.now();
        if (!(this.parameters.get("start") == null)) {
            sprintStart = DateTimeParser.parseDate(this.parameters.get("start"));
        } else {
            throw new DukeException("no start date");
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        String sprintGoal;
        if (!(this.parameters.get("goal").isBlank())) {
            sprintGoal = this.parameters.get("goal");
        } else {
            throw new DukeException("no goal");
        }
        allSprint.addSprint(proj, sprintGoal, sprintStart, sprintEnd);

        LocalDate projEndDate = sprintStart.plusDays(proj.getProjectDuration() - 1);
        proj.setStartDate(sprintStart);
        proj.setEndDate(projEndDate);

        Ui.showToUserLn("Project will start along with the newly created sprint");
        Ui.showToUserLn("Project period: " + sprintStart + " to " + projEndDate);
        printCreatedSprint();

    }

    private void createSubsequentSprint(Project proj) {

        String sprintGoal = this.parameters.get("goal");
        Sprint prevSprint = allSprint.getSprint(allSprint.size() - 1);
        LocalDate sprintStart = prevSprint.getEndDate().plusDays(1);
        if (DateTimeParser.diff(proj.getEndDate(), sprintStart) >= 0) {
            Ui.showToUserLn("\nAll sprints are already created.");
            return;
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        allSprint.addSprint(proj, sprintGoal, sprintStart, sprintEnd);
        if (!this.parameters.containsKey("start")) {
            Ui.showToUserLn(Messages.MESSAGE_CREATE_SUB_SPRINT);
        }
        printCreatedSprint();
    }

    private boolean validateParams() {
        return this.parameters.containsKey("goal");
    }

    private void printCreatedSprint() {
        Sprint createdSprint = allSprint.getSprint(allSprint.size() - 1);
        Ui.showToUserLn(createdSprint.toString());
    }
}
