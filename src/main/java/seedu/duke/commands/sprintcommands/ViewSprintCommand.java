package seedu.duke.commands.sprintcommands;

import seedu.duke.data.Project;
import seedu.duke.data.Sprint;
import seedu.duke.data.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;
import java.util.Hashtable;

public class ViewSprintCommand extends SprintCommand {
    SprintList allSprint;

    public ViewSprintCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    /**
     * Abstract method that execute the command.
     *
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public boolean execute(Project proj, TextUi ui) {

        allSprint = proj.getAllSprints();
        if (updateCurrentSprint()) {
            int currentSprintNo = allSprint.getCurrentSprint();
            Sprint currentSprint = allSprint.getSprint(currentSprintNo);
            ui.showToUser("------ Current Sprint ------");
            ui.showToUser("Sprint number: " + currentSprintNo);
            ui.showToUser("Sprint Goal: " + currentSprint.getGoal());
            ui.showToUser("Sprint period: " + currentSprint.getStartDate() + " to " + currentSprint.getEndDate());
            ui.showToUser("Days left: " + currentSprint.getEndDate().compareTo(LocalDate.now()));
        } else {
            checkReason(proj,ui);
        }

        return true;
    }

    public boolean updateCurrentSprint() {
        for (int i = 0; i < allSprint.size(); i++) {
            Sprint current = allSprint.getSprint(i);
            if (DateTimeParser.diff(LocalDate.now(), current.getEndDate()) >= 0
                    && DateTimeParser.diff(current.getStartDate(), LocalDate.now()) >= 0) {
                System.out.println(LocalDate.now());
                System.out.println(current.getEndDate());
                System.out.println(DateTimeParser.diff(LocalDate.now(), current.getEndDate()));
                allSprint.setCurrentSprint(i);
                return true;

            }
        }
        return false;
    }

    public void checkReason(Project proj, TextUi ui) {
        if (allSprint.size() == 0) {
            ui.showToUser("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            ui.showToUser("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            ui.showToUser("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            ui.showToUser("Latest sprint ended on " + latestSprint.getEndDate());
            ui.showToUser("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            ui.showToUser("First sprint will start on " + current.getStartDate());
        }
    }
}
