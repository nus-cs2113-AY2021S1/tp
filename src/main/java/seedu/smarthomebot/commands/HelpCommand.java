package seedu.smarthomebot.commands;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_HELP;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = "Help: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_HELP);
    }

}
