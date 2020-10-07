package commands;

import manager.chapter.CardList;
import ui.Ui;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(CardList cards, Ui ui) {
        ui.showHelpList();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
