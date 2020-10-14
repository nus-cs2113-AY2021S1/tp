package seedu.duke.command.sprint;

import seedu.duke.project.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class AllocateSprintTaskCommand extends SprintCommand {
    private SprintList allSprint;
    private ArrayList<Project> projectList;
    private Project proj;

    public AllocateSprintTaskCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList) {
        super(parameters);
        this.projectList = projectList;
    }

    public void execute() {
        proj = projectList.get(0);
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            if (validateParams()) {
                String taskId = parameters.get("taskid");
                ArrayList<String> users = new ArrayList<>();
                for (int i = 0; i < parameters.size() - 1; i++) {
                    users.add(parameters.get(Integer.toString(i)));
                }

                int currentSprintNo = allSprint.getCurrentSprintIndex();
                Sprint currentSprint = allSprint.getSprint(currentSprintNo);
                currentSprint.allocateSprintTask(Integer.parseInt(taskId), users);
            }
        } else {
            checkReason();
        }
    }

    private boolean validateParams() {
        return !parameters.get("taskid").isEmpty() && !parameters.get("0").isEmpty();
    }

    private void checkReason() {
        if (allSprint.size() == 0) {
            Ui.showToUser("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            Ui.showToUser("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            Ui.showToUser("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            Ui.showToUser("Latest sprint ended on " + latestSprint.getEndDate());
            Ui.showToUser("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            Ui.showToUser("First sprint will start on " + current.getStartDate());
        }
    }
}
