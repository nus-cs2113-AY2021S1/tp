package seedu.duke.parser;

import seedu.duke.exception.DukeException;
import java.util.Hashtable;

public class MemberParser implements ExceptionsParser {
    @Override
    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters) throws DukeException {
        if (parameters.get("0").isBlank()) {
            throw new DukeException("missing name");
        }
    }

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action)
            throws DukeException {
    }
}
