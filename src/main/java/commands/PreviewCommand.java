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
            + "Example: " + COMMAND_WORD;
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
            addIfDue(increment, chapter, deadline);
        }
    }

    private void addIfDue(int increment, DueChapter chapter, LocalDate deadline) {
        if (Scheduler.isDeadlineDueIn(deadline, increment)) {
            dueDueChapters.add(chapter);
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws ExclusionFileException {
        loadAllDueChapters(storage, ui);
        for (int increment = 0; increment < 7; increment++) {
            dueDueChapters = new ArrayList<>();
            setDueDueChapters(increment);
            printPreviewMessage(ui, increment);
            ui.printDueChapters(dueDueChapters);
        }
    }

    private void printPreviewMessage(Ui ui, int increment) {
        if (increment == 0) {
            ui.printDueByTodayMessage(dueDueChapters.size(), COMMAND_WORD);
        } else {
            ui.printDueByIncrementMessage(dueDueChapters.size(), Scheduler.getIncrementedDate(increment));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}