package seedu.duke.parser;

import seedu.duke.command.sprint.CreateSprintCommand;
import seedu.duke.command.sprint.AddSprintTaskCommand;
import seedu.duke.command.sprint.RemoveSprintTaskCommand;
import seedu.duke.command.sprint.ViewSprintCommand;
import seedu.duke.command.sprint.AllocateSprintTaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.ADDTASK;
import static seedu.duke.command.CommandSummary.REMOVETASK;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.ASSIGN;

public class SprintParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ArrayList<Project> projectList)
            throws DukeException {
        switch (action.toLowerCase()) {
        case CREATE:
            new CreateSprintCommand(parameters, projectList).execute();
            break;
        case ADDTASK:
            new AddSprintTaskCommand(parameters, projectList).execute();
            break;
        case REMOVETASK:
            if (parameters.get("0").isBlank() || !Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number");
            } else {
                new RemoveSprintTaskCommand(parameters, projectList).execute();
            }
            break;
        case VIEW:
            new ViewSprintCommand(parameters, projectList).execute();
            break;
        case ASSIGN:
            new AllocateSprintTaskCommand(parameters, projectList).execute();
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
