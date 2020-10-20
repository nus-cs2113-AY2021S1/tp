package seedu.duke.parser;

import seedu.duke.command.project.ProjectCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.*;

public class ProjectParser implements ExceptionsParser {
    @Override
    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters)
            throws DukeException {
    }

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ArrayList<Project> projectList)
            throws DukeException {
        switch (action.toLowerCase()) {
        case CREATE:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(DURATION) || !parameters.containsKey(SPRINT_DURATION)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new DukeException("no title");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new DukeException("no description");
            }
            if (parameters.get(DURATION).isBlank() || !Parser.isStringContainsNumber(parameters.get(DURATION))) {
                throw new DukeException("please give a number for duration");
            }
            if (parameters.get(SPRINT_DURATION).isBlank()
                    || !Parser.isStringContainsNumber(parameters.get(SPRINT_DURATION))) {
                throw new DukeException("please give a number for sprint duration");
            } else {
                new ProjectCommand().createProjectCommand(parameters, projectList);
            }
            break;
        case VIEW:
            new ProjectCommand().viewProjectCommand(projectList);
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
