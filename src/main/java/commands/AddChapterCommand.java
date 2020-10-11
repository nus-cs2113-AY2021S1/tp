package commands;

import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import access.Access;
import storage.Storage;
import ui.Ui;

public class AddChapterCommand extends Command {
    public static final String COMMAND_WORD = "addchapter";
    private final Chapter chapter;

    public AddChapterCommand(String chapterCode) {
        this.chapter = new Chapter(chapterCode);
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.isAdminLevel() || access.isChapterLevel()) {
            System.out.println("Sorry, you currently are in the admin/chapter level, "
                    + "please go to module level first.");
            return;
        }

        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        chapters.addChapter(chapter);
        int chapterCount = chapters.getChapterCount();
        ui.showChapterAdded(chapter, chapterCount);
        access.setModule(newModule);
        storage.createChapter(chapter.getChapterName(), access.getModuleLevel());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

