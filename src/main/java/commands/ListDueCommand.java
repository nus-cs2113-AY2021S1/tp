package commands;

import access.Access;

import exception.InvalidFileFormatException;
import exception.ExclusionFileException;

import manager.chapter.DueChapter;

import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListDueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all Chapters that are due by the execution "
            + "date.\n" + "Example: " + COMMAND_WORD + "\n";
    public static final String UNABLE_TO_LOAD_EMPTY_DATABASE = "Sorry, you do not have any flashcards in the database"
            + "yet. Please try this command again once you have added some flashcards!";

    public ArrayList<DueChapter> allChapters;
    public ArrayList<DueChapter> dueChapters;
  
    private void loadAllChapters(Storage storage, Ui ui) throws InvalidFileFormatException, ExclusionFileException {
        try {
            allChapters = storage.loadAllDueChapters(ui);
        } catch (FileNotFoundException e) {
            throw new InvalidFileFormatException(UNABLE_TO_LOAD_EMPTY_DATABASE);
        }
    }

    private void setDueChapters() {
        for (DueChapter chapter : allChapters) {
            LocalDate deadline = chapter.getChapter().getDueBy();
            if (Scheduler.isDeadlineDue(deadline)) {
                dueChapters.add(chapter);
            }
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidFileFormatException,
            ExclusionFileException {
        dueChapters = new ArrayList<>();
        loadAllChapters(storage, ui);
        setDueChapters();
        ui.printDueByTodayMessage(dueChapters.size(), COMMAND_WORD);
        ui.printDueChapters(dueChapters);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

