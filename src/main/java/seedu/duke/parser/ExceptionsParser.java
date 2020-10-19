package seedu.duke.parser;

import seedu.duke.exception.DukeException;

import java.util.Hashtable;

public interface ExceptionsParser {

    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters) throws DukeException;

    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action)
            throws DukeException;
}
