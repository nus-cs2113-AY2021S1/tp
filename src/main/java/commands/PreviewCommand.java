package commands;

import access.Access;
import exception.ExclusionFileException;
import exception.InvalidFileFormatException;
import manager.chapter.DueChapter;
import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Parses through the User's existing Chapters and prints out which of them are due in the upcoming week
 * individually, starting from due at the current moment, due the next day, due two days later, to due six days
 * later.
 */
public class PreviewCommand extends Command {
    public static final String COMMAND_WORD = "preview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Forecasts the chapters due in the upcoming week.\n"
            + "Example: " + COMMAND_WORD;
    public static final String UNABLE_TO_LOAD_EMPTY_DATABASE = "Sorry, you do not have any flashcards in the database"
            + "yet. Please try this command again once you have added some flashcards!";
    public ArrayList<DueChapter> allDueChapters;
    public ArrayList<DueChapter> dueDueChapters;

    private void loadAllDueChapters(Storage storage, Ui ui) throws ExclusionFileException, InvalidFileFormatException {
        try {
            allDueChapters = storage.loadAllDueChapters(ui);
        } catch (FileNotFoundException e) {
            throw new InvalidFileFormatException(UNABLE_TO_LOAD_EMPTY_DATABASE);
        }
    }

    private void setDueDueChapters(int increment) {
        for (DueChapter chapter : allDueChapters) {
            LocalDate deadline = chapter.getChapter().getDueBy();
            addIfDue(increment, chapter, deadline);
        }
    }

    private void printPreviewMessage(Ui ui, int increment) {
        if (increment == 0) {
            ui.printDueByTodayMessage(dueDueChapters.size(), COMMAND_WORD);
        } else {
            ui.printDueByIncrementMessage(dueDueChapters.size(), Scheduler.getIncrementedDate(increment));
        }
    }

    private void addIfDue(int increment, DueChapter chapter, LocalDate deadline) {
        if (Scheduler.isDeadlineDueIn(deadline, increment)) {
            dueDueChapters.add(chapter);
        }
    }

    /**
     * Executes the "preview" command.
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the modules, chapters or cards
     * @param storage storage which the command uses to load or write data to storage files
     * @throws InvalidFileFormatException if the database is currently empty and there are no Chapters to parse
     * @throws ExclusionFileException if there are errors with the Exclusion File
     */
    @Override
    public void execute(Ui ui, Access access, Storage storage) throws ExclusionFileException,
            InvalidFileFormatException {
        loadAllDueChapters(storage, ui);
        for (int increment = 0; increment < 7; increment++) {
            dueDueChapters = new ArrayList<>();
            setDueDueChapters(increment);
            printPreviewMessage(ui, increment);
            ui.printDueChapters(dueDueChapters);
        }
    }

    /**
     * Used to determine if this Command is the "exit" command.
     * @return false as this is not the "exit" command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}