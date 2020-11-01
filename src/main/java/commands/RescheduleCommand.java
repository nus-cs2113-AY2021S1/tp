package commands;

import access.Access;
import exception.InvalidInputException;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;

public class RescheduleCommand extends Command {
    public static final String COMMAND_WORD = "reschedule";

    public static final String PARAMETERS = " CHAPTER_INDEX DATE(yyyy-MM-dd)";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reschedules the due date of a chapter.\n"
            + "Only available at module level.\n"
            + "Parameters:" + PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1 2020-12-12\n";

    private static final String MESSAGE_SAME_DATE = "%1$s has the same due date as what you entered: %2$s\n";
    private static final String MESSAGE_BEFORE_RESCHEDULE = "%1$s has the following due date: %2$s\n";
    private static final String MESSAGE_AFTER_RESCHEDULE = "It has been rescheduled to: %1$s\n";
    private static final String MESSAGE_NO_DUE_DATE = "No due date";

    private final int index;
    private LocalDate date;

    public RescheduleCommand(int index, LocalDate date) {
        this.index = index;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException {
        String result = reschedule(access, storage);
        ui.showToUser(result);
    }

    private String reschedule(Access access, Storage storage) throws InvalidInputException {
        assert access.isModuleLevel() : "Not module level";
        ChapterList chapters = access.getModule().getChapters();
        try {
            Chapter chapter = chapters.getChapter(index);
            LocalDate dueBy = chapter.getDueBy();

            if (dueBy != null && date.isEqual(dueBy)) {
                return String.format(MESSAGE_SAME_DATE, chapter.getChapterName(), date);
            }

            chapter.setDueBy(date, storage, access);
            StringBuilder result = new StringBuilder();
            result.append(String.format(MESSAGE_BEFORE_RESCHEDULE, chapter.getChapterName(),
                    (dueBy == null) ? MESSAGE_NO_DUE_DATE : dueBy));
            result.append(String.format(MESSAGE_AFTER_RESCHEDULE, date));
            return result.toString();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_INDEX_RANGE, CHAPTER));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
