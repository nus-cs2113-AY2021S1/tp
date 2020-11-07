package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.parser.DateTimeParser.catchDateFormat;

public class CreateSprintCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private String sprintGoal;
    private LocalDate sprintStart;
    private LocalDate sprintEnd;

    /**
     * Creates a new CreateSprint command with arguments.
     */
    public CreateSprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
        this.sprintStart = LocalDate.now();
    }

    /**
     * Validate the parameters and execute the command.
     */
    public void execute() {
        try {
            checkProjectExist(-1);
            chooseProject();
            prepareParameters();
            checkAllSprintCreated();

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            updateProjectDates();
            sprintList.addSprint(this.projOwner, sprintGoal, sprintStart, sprintEnd);
            printCreatedSprint();
            logExecution();
        } catch (DukeException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
        }
    }

    /**
     * Prepare the parameters.
     *
     * @First_Sprint - start parameter is used for project and sprint start date
     * @Subsequent_Sprints - New sprint will start right after previous sprint ends
     */
    private void prepareParameters() throws DukeException {
        this.sprintGoal = this.parameters.get("goal");
        if (this.sprintGoal.trim().equals("")) {
            throw new DukeException("Goal cannot be empty.");
        }
        if (checkIsFirstSprint()) {
            if (this.parameters.containsKey("start")) {
                String date = this.parameters.get("start");
                try {
                    String correctDate = catchDateFormat(date);
                    this.sprintStart = DateTimeParser.parseDate(correctDate);
                } catch (DukeException e) {
                    Ui.showError(e.getMessage());
                }
            }
        } else {
            Sprint prevSprint = sprintList.getSprint(sprintList.size());
            this.sprintStart = prevSprint.getEndDate().plusDays(1);
        }
        this.sprintEnd = this.sprintStart.plusDays(this.projOwner.getSprintLength() - 1);
    }

    /**
     * Update the Project start/end dates and output results.
     * Only when the newly created sprint is the first Sprint
     */
    private void updateProjectDates() {
        if (checkIsFirstSprint()) {
            LocalDate projEndDate = this.sprintStart.plusDays(this.projOwner.getProjectDuration() - 1);
            this.projOwner.setStartDate(this.sprintStart);
            this.projOwner.setEndDate(projEndDate);
            Ui.showToUserLn("First Sprint: Project will start along with the newly created sprint");
            Ui.showToUserLn("Project period: " + this.sprintStart + " to " + projEndDate);
        } else {
            printRedundantStart();
        }
    }

    /**
     * Prints newly created sprint.
     */
    private void printCreatedSprint() {
        Sprint createdSprint = sprintList.getSprint(sprintList.size());
        Ui.showToUser(createdSprint.toString());
    }

    /**
     * Print message if -start tag is specified for subsequent sprint.
     */
    private void printRedundantStart() {
        if (this.parameters.containsKey("start")) {
            Ui.showToUserLn("Not first sprint: Start tag will be ignored and "
                    + "new sprint will start right after previous sprint ends.");
        } else {
            Ui.showToUserLn("Not first sprint: New sprint will start right after previous sprint ends.");
        }
    }

    /**
     * Check if there is room for new sprints.
     * Not executed for first sprint
     *
     * @throws DukeException if all sprints are created
     */
    private void checkAllSprintCreated() throws DukeException {
        if (!checkIsFirstSprint()) {
            if (DateTimeParser.diff(this.projOwner.getEndDate(), this.sprintStart) >= 0) {
                throw new DukeException("All sprints are already created.");
            }
        }
    }

    /**
     * Check if the new sprint will be the first sprint for the project.
     *
     * @return true if sprintList size is 0
     */
    private boolean checkIsFirstSprint() {
        return sprintList.size() == 0;
    }

    /**
     * Add entry to logger that execution is successful.
     */
    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Created New Sprint - "
                + this.sprintList.getSprint(this.sprintList.size()).getGoal()
                + " for Project " + this.projOwner.toIdString());
    }
}
