package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

import static common.Messages.MODULE;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_ACCESS;

public class AddChapterCommand extends AddCommand {
    public static final String ACCESS_LEVEL = MODULE;
    public static final String CHAPTER_PARAMETERS = " CHAPTER_NAME";
    public static final String CHAPTER_MESSAGE_USAGE = COMMAND_WORD + ": Adds a chapter to the module. \n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " Chapter 1\n";

    private String chapter;

    public AddChapterCommand(String chapter) {
        this.chapter = chapter;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException, IOException {

        String result = "";

        if (access.isModuleLevel()) {
            Chapter chapter = new Chapter(this.chapter, rateChapter(), storage, access);
            result = addChapter(access, storage, chapter);
        } else {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), ACCESS_LEVEL));
        }

        ui.showToUser(result);
    }

    private String addChapter(Access access, Storage storage, Chapter chapter) throws IOException {
        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        chapters.addChapter(chapter);
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
