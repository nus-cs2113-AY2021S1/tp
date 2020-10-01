package seedu.duke.command;

/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    private static final String HELP_STRING = "This is a help command. Currently all commands are recognise as Help "
            + "command.";

    @Override
    public String execute() {
        return HELP_STRING;
    }
}
