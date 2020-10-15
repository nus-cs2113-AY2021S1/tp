package seedu.duke.command.sprint;

import seedu.duke.project.Project;
import seedu.duke.sprint.Member;
import seedu.duke.sprint.Sprint;
import seedu.duke.sprint.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        if (projectList.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }
        proj = projectList.get(0);
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            if (validateParams()) {
                int taskId = Integer.parseInt(this.parametersInHT.get("task").trim());
                String[] userIds = this.parametersInHT.get("user").split(" ");
                for (String id : userIds) {
                    Member mem = proj.getProjectMember().getMember(id.trim());
                    if (mem == null) {
                        Ui.showError("User not found.");
                        return;
                    } else {
                        mem.allocateTask(taskId);
                        proj.getProjectBacklog().getTask(taskId).allocateToMember(mem.getUserId());
                    }
                }
                Ui.showToUserLn(proj.getProjectBacklog().getTask(taskId).getTitle()
                        + " assigned to "
                        + Arrays.toString(userIds));
            }
        } else {
            checkReason();
        }
    }

    private boolean validateParams() {
        return !this.parametersInHT.get("task").isEmpty() && !this.parametersInHT.get("user").isEmpty();
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
