package seedu.duke.command.project;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DESCRIPTION;


public class CreateProjectCommand extends ProjectCommand {

    private final ArrayList<Project> projectList;

    public CreateProjectCommand(Hashtable<String, String> parameters, ArrayList<Project> projectList) {
        super(parameters);
        this.projectList = projectList;
    }

    public void execute() throws DukeException {

        String title;
        title = this.parameters.get(TITLE).trim();

        String description;
        description = parameters.get(DESCRIPTION).trim();

        int duration;
        duration = Integer.parseInt(parameters.get(DURATION).trim());

        int sd;
        sd = Integer.parseInt(parameters.get(SPRINT_DURATION).trim());

        if (sd > duration || duration % sd != 0) {
            throw new DukeException(Messages.MESSAGE_INVALID_DURATION);

        }

        Project proj = new Project(title, description, duration, sd);
        Ui.showToUserLn("Project successfully created.");
        projectList.add(proj);
        Ui.showToUserLn(proj.toString());
    }
}
