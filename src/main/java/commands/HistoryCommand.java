package commands;

import access.Access;
import manager.history.History;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    public static final String DATE_PARAMETER = " DATE";

    private String date;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the revision completed in the session/in a day. \n"
            + "Parameters:" + DATE_PARAMETER + "\n"
            + "Example: " + COMMAND_WORD + "2020-10-10\n";

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
