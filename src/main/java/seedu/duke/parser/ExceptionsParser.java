package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

public interface ExceptionsParser {

    /**
     * Parses the different parts of the user input commands.
     * @param parameters parameters of the command action.
     * @param action command action word.
     * @param projectListManager manager of projects
     * @return a command to be executed by SCRUMptious.
     * @throws DukeException when invalid commands or parameters are input.
     */
    Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                            ProjectManager projectListManager)
            throws DukeException;
}
