package seedu.duke.parser;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

public interface ExceptionsParser {

    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectManager projectListManager)
            throws DukeException;
}
