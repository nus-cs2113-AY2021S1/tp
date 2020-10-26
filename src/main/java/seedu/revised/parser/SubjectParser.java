package seedu.revised.parser;

import seedu.revised.command.subject.AddSubjectCommand;
import seedu.revised.command.subject.DeleteSubjectCommand;
import seedu.revised.command.subject.ExitSubjectCommand;
import seedu.revised.command.subject.ExportSubjectCommand;
import seedu.revised.command.subject.FindSubjectCommand;
import seedu.revised.command.subject.ListSubjectCommand;
import seedu.revised.command.subject.GoToSubjectCommand;
import seedu.revised.command.subject.SorrySubjectCommand;
import seedu.revised.command.subject.SubjectCommand;
import seedu.revised.command.subject.QuizSubjectCommand;
import seedu.revised.command.subject.ResultSubjectCommand;
import seedu.revised.command.subject.HelpSubjectCommand;

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
        } else if (fullCommand.equals("list")) {
            return new ListSubjectCommand();
        } else if (fullCommand.equals("export")) {
            return new ExportSubjectCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindSubjectCommand(fullCommand);
        } else if (fullCommand.equals("help")) {
            return new HelpSubjectCommand();
        } else if (fullCommand.startsWith("subject")) {
            return new GoToSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("quiz")) {
            return new QuizSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("results")) {
            return new ResultSubjectCommand(fullCommand);
        } else {
            return new SorrySubjectCommand();
        }
    }
}