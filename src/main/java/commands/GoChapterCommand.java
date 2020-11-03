package commands;

import access.Access;
import manager.card.Card;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GoChapterCommand extends GoCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter level. \n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " Chapter 1\n";

    private String chapter;

    public GoChapterCommand(String chapter) {
        this.chapter = chapter;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        String result = goChapter(access, storage);
        if (result.equals("")) {
            return;
        }
        ui.showToUser(result);
    }

    private String goChapter(Access access, Storage storage) {
        boolean isLevelExist = false;
        String result = "";
        String currentChapter = this.chapter;
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        for (Chapter chapter : allChapters) {
            String chapterName = chapter.getChapterName();
            if (currentChapter.equalsIgnoreCase(chapterName)) {
                access.setChapterLevel(chapterName);
                isLevelExist = true;
                try {
                    ArrayList<Card> allCards = storage.loadCard(access.getModuleLevel(), chapter.getChapterName());
                    if (allCards.size() == 0) {
                        result = "This is a new chapter, you can try to add flashcards inside!";
                    }
                    chapter.setCards(allCards);
                    access.setChapter(chapter);
                } catch (FileNotFoundException e) {
                    result = "The chapter file cannot be found.";
                }
                break;
            }
        }
        if (!isLevelExist) {
            result = "Sorry, I cannot find this chapter, please add this chapter first";
        }
        return result;
    }
}
