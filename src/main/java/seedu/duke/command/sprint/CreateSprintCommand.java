package seedu.duke.command.sprint;

import seedu.duke.common.Messages;
import seedu.duke.model.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;
import java.util.Hashtable;

public class CreateSprintCommand extends SprintCommand {
    SprintList allSprint;

    /**
     * Creates a new DELETE command with arguments.
     */
    public CreateSprintCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    /**
     * Abstract method that execute the command.
     *
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public boolean execute(Project proj, TextUi ui) {
        if (validateParams()) {
            allSprint = proj.getAllSprints();
            if (allSprint.size() == 0) {
                createFirstSprint(proj, ui);
            } else {
                createSubsequentSprint(proj, ui);
            }
        }
        return false;
    }

    private void createFirstSprint(Project proj, TextUi ui) {

        LocalDate sprintStart = LocalDate.now();
        if (!parameters.get("start").isEmpty()) {
            sprintStart = DateTimeParser.parseDate(parameters.get("start"));
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        String sprintGoal = parameters.get("goal");
        allSprint.addSprint(sprintGoal, sprintStart, sprintEnd);

        LocalDate projEndDate = sprintStart.plusDays(proj.getProjectDuration() - 1);
        proj.setStartDate(sprintStart);
        proj.setEndDate(projEndDate);

        ui.showToUser("Project will start along with the newly created sprint");
        System.out.println("Project period: " + sprintStart + " to " + projEndDate);
        printCreatedSprint(ui);

    }

    private void createSubsequentSprint(Project proj, TextUi ui) {

        String sprintGoal = parameters.get("goal");
        Sprint prevSprint = allSprint.getSprint(allSprint.size() - 1);
        LocalDate sprintStart = prevSprint.getEndDate().plusDays(1);
        if (DateTimeParser.diff(proj.getEndDate(),sprintStart) >= 0) {
            ui.showToUser("\nAll sprints are already created.");
            return;
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        allSprint.addSprint(sprintGoal, sprintStart, sprintEnd);
        if (!parameters.get("start").isEmpty()) {
            ui.showToUser(Messages.MESSAGE_CREATE_SUB_SPRINT);
        }
        printCreatedSprint(ui);
    }

    private boolean validateParams() {
        return !parameters.get("goal").isEmpty();
    }

    private void printCreatedSprint(TextUi ui) {
        Sprint created = allSprint.getSprint(allSprint.size() - 1);
        ui.showToUser("\n--- New Sprint ---");
        ui.showToUser("Goal: " + created.getGoal());
        ui.showToUser("Start Date: " + created.getStartDate());
        ui.showToUser("End Date: " + created.getEndDate());
    }
}
