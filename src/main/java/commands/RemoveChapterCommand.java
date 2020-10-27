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

public class RemoveChapterCommand extends RemoveCommand {
    private static Logger logger = KajiLog.getLogger(RemoveChapterCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes flashcard based on the index in the list. \n"
            + "Parameters: FLASHCARD_INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_INVALID_INDEX_CHAPTER = "The chapter is not found, please try again.";

    private final int removeIndex;

    public RemoveChapterCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = removeChapter(access, storage);
        ui.showToUser(result);
    }

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
            return MESSAGE_INVALID_INDEX_CHAPTER;
        }
    }
}
