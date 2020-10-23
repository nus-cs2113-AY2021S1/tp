package seedu.duke.command.sprint;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.sprint.SprintManager;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.Hashtable;

public class RemoveSprintTaskCommand extends SprintCommand {
    private SprintManager allSprint;
    private ProjectManager projectManager;
    private Project proj;

    public RemoveSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectListManager) {
        super(parameters);
        this.projectManager = projectListManager;
    }

    public void execute() {
        assert !projectManager.isEmpty() : "No project\n";
        if (projectManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }
        proj = projectManager.getProject();
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            int currentSprintNo = allSprint.getCurrentSprintIndex();
            Sprint currentSprint = allSprint.getSprint(currentSprintNo);
            for (String entry : parameters.values()) {
                try {
                    int taskId = Integer.parseInt(entry);
                    if (!currentSprint.checkTaskExist(taskId)) {
                        Ui.showError("This task do not exist.");
                        return;
                    }
                    Ui.showToUser(proj.getProjectBacklog().getTask(taskId).getTitle() + " removed from sprint.\n");
                    currentSprint.removeSprintTask(taskId);
                } catch (NumberFormatException e) {
                    Ui.showError("Invalid parameters.");
                }
            }
        } else {
            checkReason();
        }
    }

    public void checkReason() {
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
