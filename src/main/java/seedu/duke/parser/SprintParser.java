package seedu.duke.parser;

import seedu.duke.exception.DukeException;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.ADDTASK;
import static seedu.duke.command.CommandSummary.DELETETASK;
import static seedu.duke.command.CommandSummary.ASSIGN;

public class SprintParser implements ExceptionsParser {
    @Override
    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters) throws DukeException {
    }

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action)
            throws DukeException {
        switch (action.toLowerCase()) {
        case CREATE:
            break;
        case ADDTASK:
        case DELETETASK:
            if (parameters.get("0").isBlank() || !Parser.stringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number");
            }
            break;
        case ASSIGN:
            break;
        default:
            break;
        }
    }
}
