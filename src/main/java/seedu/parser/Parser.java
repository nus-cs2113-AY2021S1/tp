package seedu.parser;

import seedu.commands.Add;
import seedu.commands.Bye;
import seedu.commands.Clear;
import seedu.commands.Command;
import seedu.commands.Delete;
import seedu.commands.Edit;
import seedu.commands.Help;
import seedu.commands.List;
import seedu.commands.Search;
import seedu.commands.Undo;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.UnknowCommandException;

public class Parser {

    public Command processRaw(String rawInput) throws
            UnknowCommandException, InvalidCommandException, InvalidTaskNumberException {
        if (rawInput.startsWith(Help.COMMAND_WORD)) {
            return new Help();
        } else if (rawInput.startsWith(Add.COMMAND_WORD)) {
            return new Add(rawInput);
        } else if (rawInput.startsWith(List.COMMAND_WORD)) {
            return new List(rawInput);
        } else if (rawInput.startsWith(Bye.COMMAND_WORD)) {
            return new Bye();
        } else if (rawInput.startsWith(Edit.COMMAND_WORD)) {
            return new Edit(rawInput);
        } else if (rawInput.startsWith(Clear.COMMAND_WORD)) {
            return new Clear();
        } else if (rawInput.startsWith(Search.COMMAND_WORD)) {
            return new Search(rawInput);
        } else if (rawInput.startsWith(Delete.COMMAND_WORD)) {
            return new Delete(rawInput);
        } else if (rawInput.startsWith(Undo.COMMAND_WORD)) {
            return new Undo();
        } else {
            throw new UnknowCommandException();
        }
    }
}
