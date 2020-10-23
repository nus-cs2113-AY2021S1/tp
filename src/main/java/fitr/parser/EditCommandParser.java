package fitr.parser;

import fitr.command.Command;
import fitr.command.EditCommand;
import fitr.command.EditProfileCommand;
import fitr.command.InvalidCommand;
import fitr.common.Commands;
import fitr.common.Messages;
import fitr.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditCommandParser {
    private static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<argument>\\S+)(?<index>.*)");
    private final String argument;

    public EditCommandParser(String argument) {
        this.argument = argument;
    }

    public Command editCommand() {
        Matcher matcher = ARGUMENT_FORMAT.matcher(argument.trim());

        if (!matcher.matches()) {
            Ui.printInvalidCommandError();
        }

        String editArgument = matcher.group("argument");

        switch (editArgument) {
        case Messages.EDIT_NAME:
        case Messages.EDIT_AGE:
        case Messages.EDIT_GENDER:
        case Messages.EDIT_HEIGHT:
        case Messages.EDIT_WEIGHT:
        case Messages.EDIT_FITNESS:
            return new EditProfileCommand(editArgument);
        case Commands.COMMAND_EXERCISE:
        case Commands.COMMAND_FOOD:
            return new EditCommand(argument);
        default:
            return new InvalidCommand(argument);
        }
    }
}
