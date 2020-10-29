package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.project.CreateProjectCommand;
import seedu.duke.command.project.ListProjectCommand;
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
import static seedu.duke.command.CommandSummary.LIST;

public class ProjectParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {
        switch (action.toLowerCase()) {
        //Creates a new project and adds it to the ProjectManager
        case CREATE:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(DURATION) || !parameters.containsKey(SPRINT_DURATION)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new DukeException("No title.");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new DukeException("No description.");
            }
            if (parameters.get(DURATION).isBlank() || !ParserManager.isStringIntParsable(parameters.get(DURATION))) {
                throw new DukeException("Please give a number for duration.");
            }
            if (parameters.get(SPRINT_DURATION).isBlank()
                    || !ParserManager.isStringIntParsable(parameters.get(SPRINT_DURATION))) {
                throw new DukeException("Please give a number for sprint duration.");
            } else {
                return new CreateProjectCommand(parameters, projectListManager);
            }
            // View the current selected project, and the details of the same.
        case VIEW:
            assert projectListManager.size() != 0 : "Invalid Input";
            if (!parameters.isEmpty()) {
                throw new DukeException("Invalid action!");
            } else {
                return new ViewProjectCommand(parameters, projectListManager);
            }
            // Select the project on which all commands are executed.
        case SELECT:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                throw new DukeException("Please do not enter dashes.");
            }
            if (!ParserManager.isStringIntParsable(parameters.get("0"))) {
                throw new DukeException("Please give a project number.");
            }
            // Get index of the project to select
            try {
                int index = Integer.parseInt(parameters.get("0"));
                if (index <= projectListManager.size() && index > 0) {
                    return new SelectProjectCommand(parameters, projectListManager);
                } else {
                    throw new DukeException("Invalid index, no corresponding project exists.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid input.");
            }

            // Show a list of all projects added, with id to select it.
        case LIST:
            assert projectListManager.size() != 0 : "Invalid Input";
            if (projectListManager.isEmpty()) {
                throw new DukeException("There are no projects added.");
            } else {
                return new ListProjectCommand(parameters, projectListManager);
            }
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
