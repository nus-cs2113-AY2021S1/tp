package seedu.parser;


import seedu.commands.*;

import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidFormatException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.UnknownCommandException;

import java.util.regex.Matcher;

public class Parser {


    public Command processRaw(String rawInput) throws
            UnknownCommandException, InvalidCommandException, InvalidTaskNumberException, InvalidFormatException {
        Matcher matcher;
        String commandWord = getCommandWord(rawInput);
        String commandArgs = getCommandArgs(rawInput);
        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            matcher = ListCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new ListCommand("-d".equals(matcher.group("dateFlag")),
                        "-p".equals(matcher.group("priorityFlag")),
                        "-w".equals(matcher.group("displayByWeek")),
                        "-m".equals(matcher.group("displayByMonth")),
                        matcher.group("date"));
            } else {
                throw new InvalidCommandException();
            }
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case AddCommand.COMMAND_WORD:
            matcher = AddCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new AddCommand(matcher.group("description"), matcher.group("date"), matcher.group("st"),
                        matcher.group("et"), matcher.group("priority"), matcher.group("reminder"),
                        matcher.group("t"));
            } else {
                throw new InvalidFormatException();
            }
        case EditCommand.COMMAND_WORD:
            matcher = EditCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new EditCommand(matcher.group("key"), matcher.group("description"),
                        matcher.group("date"), matcher.group("st"), matcher.group("et"),
                        matcher.group("priority"), matcher.group("reminder"), matcher.group("t"));
            } else {
                throw new InvalidFormatException();
            }
        case SearchCommand.COMMAND_WORD:
            matcher = SearchCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new SearchCommand(matcher.group("toSearch"));
            } else {
                throw new InvalidCommandException();
            }
        case DeleteCommand.COMMAND_WORD:
            matcher = DeleteCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new DeleteCommand(matcher.group("key"));
            } else {
                throw new InvalidCommandException();
            }
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();
        default:
            throw new UnknownCommandException();
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
        String commandArgs = "";
        if (rawInput.contains(" ")) {
            String[] split = rawInput.split(" ", 2);
            commandArgs = split[1];
        }
        return commandArgs.trim();
    }
}
