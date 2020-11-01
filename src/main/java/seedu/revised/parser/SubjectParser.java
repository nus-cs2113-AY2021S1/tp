package seedu.revised.parser;

import seedu.revised.command.subject.ListAllSubjectCommand;
import seedu.revised.command.subject.AddSubjectCommand;
import seedu.revised.command.subject.DeleteSubjectCommand;
import seedu.revised.command.subject.ExitSubjectCommand;
import seedu.revised.command.subject.ExportSubjectCommand;
import seedu.revised.command.subject.FindSubjectCommand;
import seedu.revised.command.subject.ListSubjectCommand;
import seedu.revised.command.subject.AccessSubjectCommand;
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
        String[] tokens = fullCommand.split(" ");
        String fullCommandLowerCase = fullCommand.toLowerCase();
        String command = tokens[0].toLowerCase();

        if (fullCommandLowerCase.equals("bye")) {
            return new ExitSubjectCommand();
        } else if (fullCommandLowerCase.equals("list")) {
            return new ListSubjectCommand();
        } else if (fullCommandLowerCase.equals("list all")) {
            return new ListAllSubjectCommand();
        } else if (fullCommandLowerCase.equals("export")) {
            return new ExportSubjectCommand();
        } else if (command.equals("add")) {
            return new AddSubjectCommand(fullCommand);
        } else if (command.equals("delete")) {
            return new DeleteSubjectCommand(fullCommand);
        } else if (command.equals("find")) {
            return new FindSubjectCommand(fullCommand);
        } else if (fullCommandLowerCase.equals("help")) {
            return new HelpSubjectCommand();
        } else if (command.equals("subject")) {
            return new AccessSubjectCommand(fullCommand);
        } else if (command.equals("quiz")) {
            return new QuizSubjectCommand(fullCommand);
        } else if (command.equals("results")) {
            return new ResultSubjectCommand(fullCommand);
        } else {
            return new SorrySubjectCommand();
        }
    }
}