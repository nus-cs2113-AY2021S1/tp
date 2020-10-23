package seedu.duke.parser;

import seedu.duke.command.project.CreateProjectCommand;
import seedu.duke.command.project.SelectProjectCommand;
import seedu.duke.command.project.ViewProjectCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.SELECT;

public class ProjectParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectManager projectListManager)
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
                new CreateProjectCommand(parameters, projectListManager).execute();
            }
            break;
        case VIEW:
            if (projectListManager.size() == 0) {
                throw new DukeException("You currently have no projects created");
            } else {
                new ViewProjectCommand(parameters, projectListManager).execute();
            }
            break;
        case SELECT:
            int index = Integer.parseInt(parameters.get("0"));
            if (index <= projectListManager.size() && index > 0) {
                new SelectProjectCommand(parameters, projectListManager).execute();
            } else {
                throw new DukeException("Invalid index, no corresponding project exists");
            }
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
