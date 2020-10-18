package seedu.duke.command.project;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DURATION;

public class ProjectCommand {
    public void createProjectCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList) {

        String title;
        String description;
        String deadline;
        String sd;

        title = parameters.get(TITLE);
        description = parameters.get(DESCRIPTION);
        deadline = parameters.get(DURATION);
        sd = parameters.get(SPRINT_DURATION);


        Project proj = new Project(title, description, deadline, sd);
        Ui.showToUserLn("Project successfully created.");
        projectList.add(proj);
        Ui.showToUserLn(projectList.get(0).toString());
    }

    public void viewProjectCommand(ArrayList<Project> projectList) {
        assert projectList.size() != 0 : "No projects created \n.";

        if (projectList.isEmpty()) {
            Ui.showError("No projects are created.");
        } else {
            Ui.showToUserLn(projectList.get(0).toString());
        }
    }

}
