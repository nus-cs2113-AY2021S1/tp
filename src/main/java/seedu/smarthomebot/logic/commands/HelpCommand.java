package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.commons.Messages;

//@@author zongxian-ctrl
/**
 * Represent the command for showing help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = "Help: " + COMMAND_WORD;
    public static final String MESSAGE_HELP = Messages.LINE
            + "Here is the list of commands available:\n" + Messages.LINE
            + "\t1. " + HelpCommand.MESSAGE_USAGE + "\n"
            + "\t2. " + CreateCommand.MESSAGE_USAGE + "\n"
            + "\t3. " + RemoveCommand.MESSAGE_USAGE + "\n"
            + "\t4. " + AddCommand.MESSAGE_USAGE + "\n"
            + "\t5. " + DeleteCommand.MESSAGE_USAGE + "\n"
            + "\t6. " + OnCommand.MESSAGE_USAGE + "\n"
            + "\t7. " + OffCommand.MESSAGE_USAGE + "\n"
            + "\t8. " + ListCommand.MESSAGE_USAGE + "\n"
            + "\t9. " + UsageCommand.MESSAGE_USAGE + "\n"
            + "\t10. " + ResetCommand.MESSAGE_USAGE + "\n"
            + "\t11. " + ExitCommand.MESSAGE_USAGE + "\n";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_HELP);
    }

}
