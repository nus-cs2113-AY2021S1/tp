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
import java.util.regex.Matcher;


public class Parser {


    public Command processRaw(String rawInput) throws
            UnknowCommandException, InvalidCommandException, InvalidTaskNumberException {
        Matcher matcher;
        String commandWord = getCommandWord(rawInput);
        String commandArgs = getCommandArgs(rawInput);
        switch (commandWord) {
        case Help.COMMAND_WORD:
            return new Help();
        case List.COMMAND_WORD:
            matcher = List.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new List(" -d".equals(matcher.group("dateFlag")),
                        " -p".equals(matcher.group("priorityFlag")),
                        " -w".equals(matcher.group("displayByWeek")),
                        " -m".equals(matcher.group("displayByMonth")),
                        matcher.group("date"));
            } else {
                throw new InvalidCommandException();
            }
        case Bye.COMMAND_WORD:
            return new Bye();
        case Clear.COMMAND_WORD:
            return new Clear();
        case Add.COMMAND_WORD:
            matcher = Add.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new Add(matcher.group("description"), matcher.group("date"), matcher.group("st"),
                        matcher.group("et"), matcher.group("priority"), matcher.group("reminder"),
                        matcher.group("t"));
            } else {
                throw new InvalidCommandException();
            }
        case Edit.COMMAND_WORD:
            matcher = Edit.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new Edit(matcher.group("key"), matcher.group("description"),
                        matcher.group("date"), matcher.group("st"), matcher.group("et"),
                        matcher.group("priority"), matcher.group("reminder"), matcher.group("t"));
            } else {
                throw new InvalidCommandException();
            }
        case Search.COMMAND_WORD:
            matcher = Search.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new Search(matcher.group("toSearch"));
            } else {
                throw new InvalidCommandException();
            }
        case Delete.COMMAND_WORD:
            matcher = Delete.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new Delete(matcher.group("key"));
            } else {
                throw new InvalidCommandException();
            }
        case Undo.COMMAND_WORD:
            return new Undo();
        default:
            throw new UnknowCommandException();
        }
    }

    public String getCommandWord(String rawInput) {
        String commandWord = rawInput;
        if (rawInput.contains(" ")) {
            String[] split = rawInput.split(" ", 2);
            commandWord = split[0];
        }
        return commandWord.trim();
    }

    public String getCommandArgs(String rawInput) {
        String commandArgs = null;
        if (rawInput.contains(" ")) {
            String[] split = rawInput.split(" ", 2);
            commandArgs = split[1];
        }
        return commandArgs.trim();
    }
}
