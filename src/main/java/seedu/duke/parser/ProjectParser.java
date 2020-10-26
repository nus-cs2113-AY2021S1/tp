package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.project.CreateProjectCommand;
import seedu.duke.command.project.ListProjectCommand;
import seedu.duke.command.project.SelectProjectCommand;
import seedu.duke.command.project.ViewProjectCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;
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
            if (parameters.get(DURATION).isBlank() || !Parser.isStringContainsNumber(parameters.get(DURATION))) {
                throw new DukeException("Please give a number for duration.");
            }
            if (parameters.get(SPRINT_DURATION).isBlank()
                    || !Parser.isStringContainsNumber(parameters.get(SPRINT_DURATION))) {
                throw new DukeException("Please give a number for sprint duration.");
            } else {
                return new CreateProjectCommand(parameters, projectListManager);
            }
        case VIEW:
            if (!parameters.isEmpty()) {
                throw new DukeException("Invalid action!");
            } else {
                return new ViewProjectCommand(parameters, projectListManager);
            }
        case SELECT:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                Ui.showError("Please do not enter dashes.");
            }
            if (!Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("Please give a project number.");
            }
            int index = Integer.parseInt(parameters.get("0"));
            if (index <= projectListManager.size() && index > 0) {
                return new SelectProjectCommand(parameters, projectListManager);
            } else {
                throw new DukeException("Invalid index, no corresponding project exists.");
            }
        case LIST:
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
