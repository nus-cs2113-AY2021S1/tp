package seedu.smarthomebot.logic.commands;

import static seedu.smarthomebot.commons.Messages.MESSAGE_HELP;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = "Help: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_HELP);
    }

}
