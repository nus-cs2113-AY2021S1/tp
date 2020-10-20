package commands;

import access.Access;
import manager.history.History;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static common.Messages.CARD;

public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    public static final String DATE_PARAMETER = " DATE";
    public static final String MESSAGE_DOES_NOT_EXIST = "You haven't completed any task in the last session.";
    public static final String MESSAGE_EXIST = "Here are the tasks you have completed in the session/in a day:\n";

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
        String result = listHistory(storage);
        ui.showToUser(result);
    }

    private String listHistory(Storage storage) throws FileNotFoundException {
        ArrayList<History> histories = storage.loadHistory(date);
        int count = histories.size();
        StringBuilder result = new StringBuilder();

        if (count == 0) {
            result.append(MESSAGE_DOES_NOT_EXIST);
            return result.toString();
        }
        result.append(MESSAGE_EXIST);
        for (History h : histories) {
            if (histories.indexOf(h) == count - 1) {
                result.append(histories.indexOf(h) + 1).append(".").append(h);
            } else {
                result.append(histories.indexOf(h) + 1).append(".").append(h).append("\n");
            }
        }
        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
