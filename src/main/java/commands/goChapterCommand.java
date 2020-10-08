package commands;

import manager.chapter.CardList;
import access.Access;
import manager.chapter.Chapter;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class goChapterCommand extends Command {
    public static final String COMMAND_WORD = "gochapter";
    String chapterCode;

    public goChapterCommand(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        boolean isLevelExist = false;
        ArrayList<Chapter> chapters = access.getModule().getChapter();
        for (Chapter chapter : chapters) {
            if(chapterCode.equalsIgnoreCase(chapter.getChapter())) {
                access.setChapterLevel(chapterCode);
                isLevelExist = true;
                try {
                    Chapter newChapter = new Chapter(chapter.getChapter(), storage.loadCard(access.getModuleLevel(), chapter.getChapter()));
                    access.setChapter(newChapter);
                } catch (FileNotFoundException e) {
                    Chapter newChapter = new Chapter(chapter.getChapter());
                    access.setChapter(newChapter);
                    System.out.println("Hihi, seems like it is a new module, you can try to add chapter inside!");
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
