package seedu.quotesify.commands;

import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents the Help Command.
 */
public class HelpCommand extends Command {

    /**
     * Executes the Help command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        ui.printHelpPage();
    }

    /**
     * Decides if the program should be terminated.
     *
     * @return decision to terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
