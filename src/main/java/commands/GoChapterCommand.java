package commands;

import manager.card.Card;
import manager.chapter.CardList;
import access.Access;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GoChapterCommand extends Command {
    public static final String COMMAND_WORD = "gochapter";
    String chapterCode;

    public GoChapterCommand(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        boolean isLevelExist = false;
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        for (Chapter chapter : allChapters) {
            if (chapterCode.equalsIgnoreCase(chapter.getChapterName())) {
                access.setChapterLevel(chapterCode);
                isLevelExist = true;
                try {
                    ArrayList<Card> allCards = storage.loadCard(access.getModuleLevel(), chapter.getChapterName());
                    if (allCards.size() == 0) {
                        System.out.println("This is a new chapter, you can try to add flashcards inside!");
                    }
                    chapter.setCards(allCards);
                    access.setChapter(chapter);
                } catch (FileNotFoundException e) {
                    System.out.println("The chapter file cannot be found.");
                }
                break;
            }
        }
        if (isLevelExist == false) {
            System.out.println("Sorry, I cannot find this chapter, please add this chapter first");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

