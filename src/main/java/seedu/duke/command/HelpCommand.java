package seedu.duke.command;

import static seedu.duke.ui.Ui.displayHelpMessage;

//@@author e0425705
/**
 * Help command.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void execute() {
        displayHelpMessage();
    }
}
