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
 * Allows the parsing of inputs provided by the user on the main level.
 */
public class SubjectParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static SubjectCommand parse(String fullCommand) {
        fullCommand = fullCommand.trim();
        String[] tokens = fullCommand.split(" ");
        String fullCommandLowerCase = fullCommand.toLowerCase();
        String command = tokens[0].toLowerCase();

        switch (command) {
        case "results":
            return new ResultSubjectCommand(fullCommand);
        case "quiz":
            return new QuizSubjectCommand(fullCommand);
        case "subject":
            return new AccessSubjectCommand(fullCommand);
        case "find":
            return new FindSubjectCommand(fullCommand);
        case "add":
            return new AddSubjectCommand(fullCommand);
        case "delete":
            return new DeleteSubjectCommand(fullCommand);
        default:
            switch (fullCommandLowerCase) {
            case "export":
                return new ExportSubjectCommand();
            case "help":
                return new HelpSubjectCommand();
            case "bye":
                return new ExitSubjectCommand();
            case "list" :
                return new ListSubjectCommand();
            case "list all":
                return new ListAllSubjectCommand();
            default:
                return new SorrySubjectCommand();
            }
        }
    }
}
