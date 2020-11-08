package commands;

import access.Access;
import common.KajiLog;
import exception.StorageDataException;
import exception.InvalidInputException;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.util.logging.Logger;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MESSAGE_ITEM_EXISTED;


/**
 * Edits the chapter name.
 */
public class EditChapterCommand extends EditCommand {
    private static Logger logger = KajiLog.getLogger(EditChapterCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the chapter name.\n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 2 Chapter 2\n";

    public static final String MESSAGE_SAME_NAME = "%1$s has the same chapter name as what you entered: %2$s\n";

    private final int editIndex;
    private String chapter;

    /**
     * Creates an EditChapterCommand to edit to the specified {@code chapter}.
     *
     * @param editIndex of the chapter in the list of chapters to edit
     * @param chapter name to edit the chapter to
     */
    public EditChapterCommand(int editIndex, String chapter) {
        this.editIndex = editIndex;
        this.chapter = chapter;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, StorageDataException {
        String result = editChapter(access, storage);
        ui.showToUser(result);
    }

    /**
     * Edits the name of the chapter.
     *
     * @param access to get the list of chapters
     * @param storage to rename the storage file
     * @return result to be displayed
     * @throws InvalidInputException if the index is invalid
     * @throws StorageDataException if there is an error renaming the storage file
     */
    private String editChapter(Access access, Storage storage) throws InvalidInputException, StorageDataException {
        assert access.isModuleLevel() : "Not module level";
        assert !chapter.isEmpty() : "The chapter name is missing.";
        ChapterList chapters = access.getModule().getChapters();
        try {
            Chapter chapter = chapters.getChapter(editIndex);

            if (this.chapter.equals(chapter.getChapterName())) {
                return String.format(MESSAGE_SAME_NAME, chapter.getChapterName(), this.chapter);
            }

            if (chapters.checkChapterExistence(this.chapter.toLowerCase())
                    && !this.chapter.toLowerCase().equals(chapter.getChapterName().toLowerCase())) {
                return String.format(MESSAGE_ITEM_EXISTED, CHAPTER, this.chapter, CHAPTER);
            }

            logger.info("Renaming chapter: " + chapter);
            storage.renameChapter(this.chapter, access.getModule(), chapter);
            StringBuilder result = new StringBuilder();
            result.append(prepareBeforeEdit(CHAPTER, chapter.toString()));
            chapter.setChapterName(this.chapter);
            logger.info("Chapter successfully renamed to: " + chapter);
            result.append(prepareAfterEdit(CHAPTER, chapter.toString()));
            return result.toString();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_INDEX_RANGE, CHAPTER));
        }
    }
}
