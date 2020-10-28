package commands;

import access.Access;
import exception.ExclusionFileException;
import manager.chapter.DueChapter;
import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PreviewCommand extends Command {
    public static final String COMMAND_WORD = "preview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Forecasts the chapters due in the upcoming week.\n"
            + "Example: " + COMMAND_WORD + "\n";
    public static final String UNABLE_TO_LOAD_EMPTY_DATABASE = "Sorry, you do not have any flashcards in the database"
            + "yet. Please try this command again once you have added some flashcards!";
    public ArrayList<DueChapter> allDueChapters;
    public ArrayList<DueChapter> dueDueChapters;

    private void loadAllDueChapters(Storage storage, Ui ui) throws ExclusionFileException {
        try {
            allDueChapters = storage.loadAllDueChapters(ui);
        } catch (FileNotFoundException e) {
            ui.showToUser(UNABLE_TO_LOAD_EMPTY_DATABASE);
        }
    }

    private void setDueDueChapters(int increment) {
        for (DueChapter chapter : allDueChapters) {
            LocalDate deadline = chapter.getChapter().getDueBy();
            if (Scheduler.isDeadlineDueIn(deadline, increment)) {
                dueDueChapters.add(chapter);
            }
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws ExclusionFileException {
        loadAllDueChapters(storage, ui);
        for (int i = 0; i < 7; i++) {
            dueDueChapters = new ArrayList<>();
            setDueDueChapters(i);
            if (i == 0) {
                ui.printDueByTodayMessage(dueDueChapters.size(), COMMAND_WORD);
            } else {
                ui.printDueByIncrementMessage(dueDueChapters.size(), Scheduler.getIncrementedDate(i));
            }
            ui.printDueChapters(dueDueChapters);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}