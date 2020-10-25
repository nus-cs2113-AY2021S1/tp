package fitr.parser;

import fitr.command.Command;
import fitr.command.EditEntryCommand;
import fitr.command.EditProfileCommand;
import fitr.command.InvalidCommand;
import fitr.common.Commands;
import fitr.common.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditCommandParser {
    private static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<type>\\S+)(?<arguments>.*)");
    private final String fullArgument;

    public EditCommandParser(String argument) {
        this.fullArgument = argument.trim();
    }

    public Command editCommand() {
        Matcher matcher = ARGUMENT_FORMAT.matcher(fullArgument);

        if (!matcher.matches()) {
            return new InvalidCommand(fullArgument);
        }

        String editType = matcher.group("type").trim().toLowerCase();
        String arguments = matcher.group("arguments").trim();

        switch (editType) {
        case Messages.EDIT_NAME:
        case Messages.EDIT_AGE:
        case Messages.EDIT_GENDER:
        case Messages.EDIT_HEIGHT:
        case Messages.EDIT_WEIGHT:
        case Messages.EDIT_FITNESS:
            return new EditProfileCommand(editType);
        case Commands.COMMAND_EXERCISE:
        case Commands.COMMAND_FOOD:
        case Commands.COMMAND_GOAL:
            return new EditEntryCommand(editType, arguments);
        default:
            return new InvalidCommand(fullArgument);
        }
    }
}
