package seedu.duke.commands;

import static seedu.duke.common.Messages.LINE;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public void execute() {
        System.out.println(
                LINE  + HelpCommand.MESSAGE_USAGE + "\n" + LINE
                      + CreateCommand.MESSAGE_USAGE + "\n" + LINE
                      + RemoveCommand.MESSAGE_USAGE  + "\n" + LINE
                      + AddCommand.MESSAGE_USAGE + "\n" + LINE
                      + DeleteCommand.MESSAGE_USAGE + "\n" + LINE
                      + OnCommand.MESSAGE_USAGE + "\n" + LINE
                      + OffCommand.MESSAGE_USAGE + "\n" + LINE
                      + ListCommand.MESSAGE_USAGE + "\n" + LINE
                      + ExitCommand.MESSAGE_USAGE + "\n");
    }

}