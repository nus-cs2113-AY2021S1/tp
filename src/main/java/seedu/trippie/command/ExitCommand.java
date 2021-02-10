package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.TrippieData;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Shows the farewell message.
     *
     * @param ui          Ui to read user input
     * @param trippieData TrippieData object to store user data in runtime
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        ui.showFarewells();
    }
}
