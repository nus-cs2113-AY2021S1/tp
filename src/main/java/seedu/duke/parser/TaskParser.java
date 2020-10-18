package seedu.duke.parser;

import seedu.duke.command.task.TaskCommand;
import seedu.duke.exception.DukeException;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.*;

public class TaskParser implements ExceptionsParser {
    @Override
    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters) throws DukeException {
    }

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action)
            throws DukeException {

        switch (action.toLowerCase()) {
        case ADD:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(PRIORITY)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new DukeException("Please enter a title!");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new DukeException("Please enter a description!");
            }
            if (parameters.get(PRIORITY) == null) {
                throw new DukeException("Please enter a priority!");
            }
            break;
        case DELETE:
        case DONE:
            if (parameters.get("0").isBlank() || !Parser.stringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number");
            }
            break;
        case PRIORITY:
            if (!parameters.containsKey(TASK_ID) || !parameters.containsKey(PRIORITY)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TASK_ID).isBlank() || !Parser.stringContainsNumber(parameters.get(TASK_ID))) {
                throw new DukeException("Task ID entered is invalid!");
            }
            if (parameters.get(PRIORITY).isBlank()) {
                throw new DukeException("Please enter a priority!");
            }
            break;
        }
    }
}
