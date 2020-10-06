package seedu.duke.commands;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;
    private static final String line = "-------------------------------------------------------";

    @Override
    public void execute() {
        System.out.println(
                line + "\n" + HelpCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + CreateCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + RemoveCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + AddCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + DeleteCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + OnCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + OffCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + ListCommand.MESSAGE_USAGE + "\n" + line
                        + "\n" + ExitCommand.MESSAGE_USAGE + "\n");
    }

}