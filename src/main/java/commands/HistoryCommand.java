package commands;

import access.Access;
import manager.history.History;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static common.Messages.CARD;

public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    public static final String DATE_PARAMETER = " DATE";
    public static final String MESSAGE_DOES_NOT_EXIST = "You did not have any revision in this session.";
    public static final String MESSAGE_EXIST = "Here is the revision completed in the session/in a day:\n";

    private String date;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the revision completed in the session/in a day. \n"
            + "Parameters:" + DATE_PARAMETER + "\n"
            + "Example: " + COMMAND_WORD + " 2020-10-10\n";

    public HistoryCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        String result = listHistory(storage);
        ui.showToUser(result);
    }

    public static void addHistory(Access access, Storage storage, int reviseIndex) throws IOException {
        LocalDate date = java.time.LocalDate.now();
        storage.createHistory(date.toString());
        String moduleName = access.getModule().getModuleName();
        String chapterName = access.getModule().getChapters().getChapter(reviseIndex).getChapterName();
        History history = new History(moduleName, chapterName);
        ArrayList<History> histories = storage.loadHistory(date.toString());;
        histories.add(history);
        storage.saveHistory(histories, date.toString());
    }

    private String listHistory(Storage storage) {
        try {
            ArrayList<History> histories = storage.loadHistory(date);;
            StringBuilder result = new StringBuilder();
            result.append(MESSAGE_EXIST);
            for (History h : histories) {
                result.append("\n").append(histories.indexOf(h) + 1).append(".").append(h);
            }
            return result.toString();
        } catch (FileNotFoundException e) {
            return MESSAGE_DOES_NOT_EXIST;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
