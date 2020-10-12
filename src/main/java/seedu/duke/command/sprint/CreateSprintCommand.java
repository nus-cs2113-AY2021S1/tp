package seedu.duke.command.sprint;

import seedu.duke.ui.Messages;
import seedu.duke.model.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
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
     *
     */
    public void execute() {
        proj = projectList.get(0);
        if (validateParams()) {
            allSprint = proj.getAllSprints();
            if (allSprint.size() == 0) {
                createFirstSprint();
            } else {
                createSubsequentSprint();
            }
        }
    }

    private void createFirstSprint() {

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

        Ui.showToUser("Project will start along with the newly created sprint");
        System.out.println("Project period: " + sprintStart + " to " + projEndDate);
        printCreatedSprint();

    }

    private void createSubsequentSprint() {

        String sprintGoal = parameters.get("goal");
        Sprint prevSprint = allSprint.getSprint(allSprint.size() - 1);
        LocalDate sprintStart = prevSprint.getEndDate().plusDays(1);
        if (DateTimeParser.diff(proj.getEndDate(),sprintStart) >= 0) {
            Ui.showToUser("\nAll sprints are already created.");
            return;
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        allSprint.addSprint(sprintGoal, sprintStart, sprintEnd);
        if (!parameters.get("start").isEmpty()) {
            Ui.showToUser(Messages.MESSAGE_CREATE_SUB_SPRINT);
        }
        printCreatedSprint();
    }

    private boolean validateParams() {
        return !parameters.get("goal").isEmpty();
    }

    private void printCreatedSprint() {
        Sprint created = allSprint.getSprint(allSprint.size() - 1);
        Ui.showToUser("\n--- New Sprint ---");
        Ui.showToUser("Goal: " + created.getGoal());
        Ui.showToUser("Start Date: " + created.getStartDate());
        Ui.showToUser("End Date: " + created.getEndDate());
    }
}
