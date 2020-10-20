package seedu.duke.parser;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

import java.util.ArrayList;
import java.util.Hashtable;

public interface ExceptionsParser {

    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ArrayList<Project> projectList)
            throws DukeException;
}
