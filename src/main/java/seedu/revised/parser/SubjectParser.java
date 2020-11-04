package seedu.revised.parser;

import seedu.revised.command.subjectcommand.ListAllSubjectCommand;
import seedu.revised.command.subjectcommand.AddSubjectCommand;
import seedu.revised.command.subjectcommand.DeleteSubjectCommand;
import seedu.revised.command.subjectcommand.ExitSubjectCommand;
import seedu.revised.command.subjectcommand.ExportSubjectCommand;
import seedu.revised.command.subjectcommand.FindSubjectCommand;
import seedu.revised.command.subjectcommand.ListSubjectCommand;
import seedu.revised.command.subjectcommand.AccessSubjectCommand;
import seedu.revised.command.subjectcommand.SorrySubjectCommand;
import seedu.revised.command.subjectcommand.SubjectCommand;
import seedu.revised.command.subjectcommand.QuizSubjectCommand;
import seedu.revised.command.subjectcommand.ResultSubjectCommand;
import seedu.revised.command.subjectcommand.HelpSubjectCommand;

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
        String command = tokens[0];

        if (fullCommand.equals("bye")) {
            return new ExitSubjectCommand();
        } else if (fullCommand.equals("list")) {
            return new ListSubjectCommand();
        } else if (fullCommand.equals("list all")) {
            return new ListAllSubjectCommand();
        } else if (fullCommand.equals("export")) {
            return new ExportSubjectCommand();
        } else if (command.equals("add")) {
            return new AddSubjectCommand(fullCommand);
        } else if (command.equals("delete")) {
            return new DeleteSubjectCommand(fullCommand);
        } else if (command.equals("find")) {
            return new FindSubjectCommand(fullCommand);
        } else if (fullCommand.equals("help")) {
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