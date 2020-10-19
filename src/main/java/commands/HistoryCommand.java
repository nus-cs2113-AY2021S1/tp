package commands;

import access.Access;
import manager.chapter.CardList;
import manager.history.History;
import manager.history.HistoryList;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    private String date;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the tasks I have completed in the session/in a day. \n"
            + "Example: " + COMMAND_WORD + "\n";

    public HistoryCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws FileNotFoundException {
        ArrayList<History> histories = storage.loadHistory(date);
        int count = histories.size();
        ui.showHistoryList(histories, count);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
