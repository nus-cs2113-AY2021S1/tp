package seedu.duke.parser;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;

import java.util.ArrayList;
import java.util.Hashtable;

public interface ExceptionsParser {

    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectList projectListManager)
            throws DukeException;
}
