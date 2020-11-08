package commands;

import access.Access;
import common.KajiLog;
import manager.card.Card;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;

public class GoChapterCommand extends GoCommand {
    private static Logger logger = KajiLog.getLogger(GoChapterCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to chapter level. \n"
            + "Parameters:" + CHAPTER_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    private final int chapterIndex;

    public GoChapterCommand(int chapterIndex) {
        this.chapterIndex = chapterIndex;
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
        assert access.isModuleLevel() : "Not module level";
        String result = "";
        try {
            ChapterList chapters = access.getModule().getChapters();
            ArrayList<Chapter> allChapters = chapters.getAllChapters();
            Chapter chapter = allChapters.get(chapterIndex);
            ArrayList<Card> allCards = storage.loadCard(access.getModuleLevel(), chapter.getChapterName());
            if (allCards.size() == 0) {
                result = "This is a new chapter, you can try to add flashcards inside!";
            }
            access.setChapterLevel(chapter.getChapterName());
            chapter.setCards(allCards);
            access.setChapter(chapter);
            logger.info("Going into chapter: " + chapter.toString());
            return result;
        } catch (IndexOutOfBoundsException e) {
            result = String.format(MESSAGE_INVALID_INDEX_RANGE, CHAPTER);
            logger.info(result);
            return result;
        } catch (FileNotFoundException e) {
            result = "The chapter file cannot be found.";
            logger.info(result);
            return result;
        }
    }
}
