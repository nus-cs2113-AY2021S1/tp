package seedu.duke.parser;

import seedu.duke.command.sprint.CreateSprintCommand;
import seedu.duke.command.sprint.AddSprintTaskCommand;
import seedu.duke.command.sprint.RemoveSprintTaskCommand;
import seedu.duke.command.sprint.ViewSprintCommand;
import seedu.duke.command.sprint.AllocateSprintTaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.ADDTASK;
import static seedu.duke.command.CommandSummary.REMOVETASK;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.ASSIGN;

public class SprintParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectManager projectListManager)
            throws DukeException {
        switch (action.toLowerCase()) {
        case CREATE:
            new CreateSprintCommand(parameters, projectListManager).execute();
            break;
        case ADDTASK:
            new AddSprintTaskCommand(parameters, projectListManager).execute();
            break;
        case REMOVETASK:
            if (parameters.get("0").isBlank() || !Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number");
            } else {
                new RemoveSprintTaskCommand(parameters, projectListManager).execute();
            }
            break;
        case VIEW:
            new ViewSprintCommand(parameters, projectListManager).execute();
            break;
        case ASSIGN:
            new AllocateSprintTaskCommand(parameters, projectListManager).execute();
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
