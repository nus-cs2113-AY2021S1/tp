package seedu.duke.command.sprint;

import seedu.duke.project.Project;
import seedu.duke.sprint.Sprint;
import seedu.duke.sprint.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

public class ViewSprintCommand extends SprintCommand {
    private SprintList allSprint;
    private ArrayList<Project> projectList;
    private Project proj;

    public ViewSprintCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList) {
        super(parameters);
        this.projectList = projectList;
    }

    /**
     * Abstract method that execute the command.
     *
     */
    public void execute() {
        proj = projectList.get(0);
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            int currentSprintNo = allSprint.getCurrentSprintIndex();
            Sprint currentSprint = allSprint.getSprint(currentSprintNo);
            Ui.showToUserLn("------ Current Sprint ------");
            Ui.showToUserLn("Sprint number: " + (currentSprintNo + 1));
            Ui.showToUserLn("Sprint Goal: " + currentSprint.getGoal());
            Ui.showToUserLn("Sprint period: " + currentSprint.getStartDate() + " to " + currentSprint.getEndDate());
            Ui.showToUserLn("Days left: " + currentSprint.getEndDate().compareTo(LocalDate.now()));
            printSprintTask(currentSprint);

        } else {
            checkReason();
        }

    }

    private void printSprintTask(Sprint sprint) {
        Hashtable<Integer, ArrayList<String>> sprintTasks = sprint.getAllSprintTask();
        if (sprintTasks.size() == 0) {
            Ui.showToUserLn("No task allocated to current sprint.");
            return;
        }
        ArrayList<String> users;
        Set<Integer> keys = sprintTasks.keySet();

        Ui.showToUserLn("Sprint Tasks: " + keys.size());
        for (int key: keys) {
            Ui.showToUserLn(proj.getProjectBacklog().getTask(key).toString());
            users = sprintTasks.get(key);
            if (users.size() == 0) {
                Ui.showToUserLn("No allocation.");
            } else {
                Ui.showToUserLn("Allocated to:" + Arrays.toString(users.toArray()));
            }
        }
    }

    private void checkReason() {
        if (allSprint.size() == 0) {
            Ui.showToUserLn("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            Ui.showToUserLn("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            Ui.showToUserLn("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            Ui.showToUserLn("Latest sprint ended on " + latestSprint.getEndDate());
            Ui.showToUserLn("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            Ui.showToUserLn("First sprint will start on " + current.getStartDate());
        }
    }
}
