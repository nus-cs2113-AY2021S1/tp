package commands;

import manager.chapter.CardList;
import ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of commands available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui) {
        ui.showHelpList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
