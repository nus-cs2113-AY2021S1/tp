package seedu.duke.parser;

import seedu.duke.command.subjectcommand.*;

/**
 * Allows the parsing of inputs provided by the user.
 */
public class SubjectParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static SubjectCommand parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitSubjectCommand();
        }else if (fullCommand.equals("list")) {
            return new ListSubjectCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("subject")) {
            return new ReturnSubjectCommand(fullCommand);
        } else {
            return new SorrySubjectCommand();
        }
    }
}