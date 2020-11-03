package commands;

import access.Access;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_ITEM_EXISTED;

public class AddChapterCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a chapter to the module. \n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " Chapter 1\n";

    private String chapter;

    public AddChapterCommand(String chapter) {
        this.chapter = chapter;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        Chapter chapter = new Chapter(this.chapter);
        String result = addChapter(access, storage, chapter);
        ui.showToUser(result);
    }

    private String addChapter(Access access, Storage storage, Chapter chapter) throws IOException {
        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        boolean isChapterExist = chapters.checkChapterExistence(chapter);
        if (isChapterExist) {
            String result = String.format(MESSAGE_ITEM_EXISTED, CHAPTER, chapter.getChapterName(), CHAPTER);
            return result;
        }
        Chapter newChapter = new Chapter(this.chapter, rateChapter(), storage, access);
        chapters.addChapter(newChapter);
        int chapterCount = chapters.getChapterCount();
        access.setModule(newModule);
        storage.createChapter(chapter.getChapterName(), access.getModuleLevel());
        return prepareResult(CHAPTER, chapter.toString(), chapterCount);
    }

    public String rateChapter() {
        if (Ui.chooseToRateNewDeck()) {
            return Ui.getChoiceOfNewDeckRating();
        } else {
            return "N";
        }
    }
}
