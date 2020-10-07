package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.*;

public class ProjectCommand {
    public void createProjectCommand(Hashtable<String, String> parameters) throws DukeException {

        String title, description, deadline, sd;
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
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
        if (parameters.get(DEADLINE) != null) {
            deadline = parameters.get(DEADLINE);
        } else {
            throw new DukeException("no deadline");
        }
        if (parameters.get(SPRINT_DURATION) != null) {
            sd = parameters.get(SPRINT_DURATION);
        } else {
            throw new DukeException("no sprint interval");
        }

        Project proj = new Project(title, description, deadline, sd);
    }
}
