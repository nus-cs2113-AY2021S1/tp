package seedu.duke.parser;

import seedu.duke.exception.DukeException;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;

public class ProjectParser implements ExceptionsParser {
    @Override
    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters) throws DukeException {
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
        if (parameters.get(DURATION).isBlank() || !Parser.stringContainsNumber(parameters.get(DURATION))) {
            throw new DukeException("please give a number for duration");
        }
        if (parameters.get(SPRINT_DURATION).isBlank() ||
                !Parser.stringContainsNumber(parameters.get(SPRINT_DURATION))) {
            throw new DukeException("please give a number for sprint duration");
        }
    }

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action)
            throws DukeException {
    }
}
