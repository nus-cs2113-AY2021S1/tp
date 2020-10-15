package seedu.duke.command.project;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DURATION;

public class ProjectCommand {
    public void createProjectCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList)
            throws DukeException {

        String title;
        String description;
        String deadline;
        String sd;

        if (parameters.get(TITLE) != null) {
            title = parameters.get(TITLE);
        } else {
            throw new DukeException("no title");
        }
        if (parameters.get(DESCRIPTION) != null) {
            description = parameters.get(DESCRIPTION);
        } else {
            throw new DukeException("no description");
        }
        if (parameters.get(DURATION) != null) {
            deadline = parameters.get(DURATION);
        } else {
            throw new DukeException("no duration");
        }
        if (parameters.get(SPRINT_DURATION) != null) {
            sd = parameters.get(SPRINT_DURATION);
        } else {
            throw new DukeException("no sprint interval");
        }

        Project proj = new Project(title, description, deadline, sd);
        Ui.showToUserLn("Project successfully created.");
        projectList.add(proj);
        Ui.showToUserLn(projectList.get(0).toString());
    }

    public void viewProjectCommand(ArrayList<Project> projectList) {
        if (projectList.isEmpty()) {
            Ui.showError("No projects are created.");
        } else {
            Ui.showToUserLn(projectList.get(0).toString());
        }
    }
}
