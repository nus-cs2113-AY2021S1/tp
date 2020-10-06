package seedu.rex.parser;

import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.patient.Patient;

public class Parser {
    public static Patient readPatient(String line) {
        // to read patients from file
        return null;
    }

    public static Command parse(String fullCommand) throws RexException {
        String commandWord = fullCommand.trim().toLowerCase();
        Command command;
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        default:
            throw new RexException(Command.COMMAND_ERROR);
        }
        return command;
    }
}
