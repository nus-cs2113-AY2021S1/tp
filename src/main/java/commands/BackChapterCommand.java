package commands;

import access.Access;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class BackChapterCommand extends Command {
    public static final String COMMAND_WORD = "backchapter";
    String chapterCode;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Back to chapter level. \n"
            + "Parameters: CHAPTER_NAME\n"
            + "Example: " + COMMAND_WORD + "\n";

    public BackChapterCommand(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) throws IOException {
        boolean isLevelExist = false;
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        for (Chapter chapter : allChapters) {
            if (chapterCode.equalsIgnoreCase(chapter.getChapterName())) {
                access.setChapterLevel(chapterCode);
                isLevelExist = true;
                storage.saveCards(cards, access.getModuleLevel(), chapter.getChapterName());
                access.setChapter(chapter);
                break;
            }
        }
        if (!isLevelExist) {
            System.out.println("Sorry, I cannot find this chapter, please add this chapter first");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
