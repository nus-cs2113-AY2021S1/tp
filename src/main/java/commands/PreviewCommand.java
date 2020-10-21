package commands;

import access.Access;
import manager.chapter.DueChapter;
import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import access.Access;

import manager.chapter.CardList;
import manager.chapter.DueChapter;

import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PreviewCommand extends Command {
    public static final String COMMAND_WORD = "preview";
    public ArrayList<DueChapter> allChapters;
    public ArrayList<DueChapter> dueChapters;

    private void loadAllChapters(Storage storage, Ui ui) {
        try {
            allChapters = storage.loadAllDueChapters();
        } catch (FileNotFoundException e) {
            ui.showToUser(Ui.UNABLE_TO_LOAD_EMPTY_DATABASE);
        }
    }

    private void setDueChapters(int increment) {
        for (DueChapter chapter : allChapters) {
            LocalDate deadline = chapter.getChapter().getDueBy();
            if (Scheduler.isDeadlineDueIn(deadline, increment)) {
                dueChapters.add(chapter);
            }
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        loadAllChapters(storage, ui);
        for (int i = 0; i < 7; i++) {
            dueChapters = new ArrayList<>();
            setDueChapters(i);
            if (i == 0) {
                ui.printDueByTodayMessage(dueChapters.size(), COMMAND_WORD);
            } else {
                ui.printDueByIncrementMessage(dueChapters.size(), Scheduler.getIncrementedDate(i));
            }
            ui.printDueChapters(dueChapters);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
