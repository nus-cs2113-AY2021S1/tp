package seedu.parser;

import seedu.commands.*;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.UnknowCommandException;

public class Parser {

    public Command processRaw(String rawInput) throws
            UnknowCommandException, InvalidCommandException {
        if (rawInput.startsWith(Help.COMMAND_WORD)) {
            return new Help();
        } else if (rawInput.startsWith(Add.COMMAND_WORD)) {
            return new Add(rawInput);
        } else if (rawInput.startsWith(List.COMMAND_WORD)) {
            return new List();
        } else if (rawInput.startsWith(Bye.COMMAND_WORD)) {
            return new Bye();
        } else if (rawInput.startsWith(Edit.COMMAND_WORD)) {
            return new Edit(rawInput);
        } else {
            throw new UnknowCommandException();
        }
    }
}
