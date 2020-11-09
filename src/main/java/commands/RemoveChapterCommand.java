package commands;

import access.Access;
import common.KajiLog;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;

//@@author neojiaern
/**
 * Removes a chapter from the module.
 */
public class RemoveChapterCommand extends RemoveCommand {
    private static Logger logger = KajiLog.getLogger(RemoveChapterCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes chapter based on the index in the list. \n"
            + "Parameters: " + CHAPTER_PARAMETER + "\n" + "Example: " + COMMAND_WORD + " 2\n";

    private final int removeIndex;

    public RemoveChapterCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    /**
     * Executes the RemoveChapterCommand by calling the relevant methods.
     *
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the chapters
     * @param storage storage which the command uses to load or write data to storage files
     * @throws IOException if there is error in loading or writing data to storage files
     */
    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = removeChapter(access, storage);
        ui.showToUser(result);
    }

    /**
     * Removes a chapter from the module.
     *
     * @param access to get the list of chapter objects
     * @param storage to remove chapter from storage
     * @return result of removing the chapter
     * @throws IOException if there is an error removing the chapter
     */
    private String removeChapter(Access access, Storage storage) throws IOException {
        assert access.isModuleLevel() : "Not module level";
        try {
            ChapterList chapters = access.getModule().getChapters();
            ArrayList<Chapter> allChapters = chapters.getAllChapters();
            Chapter chapter = allChapters.get(removeIndex);
            File directory = new File(storage.getFilePath() + "/" + access.getModule()
                    + "/" + chapter.toString() + ".txt");
            logger.info("Deleting chapter: " + chapter.toString());
            boolean isRemoved = storage.deleteDirectory(directory);
            boolean isRemovedFromDue = storage.removeChapterFromDue(
                    access.getModule().toString(), chapter.toString());
            if (!isRemoved && !isRemovedFromDue) {
                throw new IOException("There was a problem deleting chapter in directory.");
            }
            allChapters.remove(removeIndex);
            logger.info("Chapter: " + chapter.toString() + " successfully deleted.");
            return prepareResult(CHAPTER, chapter.toString(), allChapters.size());
        } catch (IndexOutOfBoundsException e) {
            String result = String.format(MESSAGE_INVALID_INDEX_RANGE, CHAPTER);
            logger.info(result);
            return result;
        }
    }
}
