package commands.list;

import access.Access;
import commands.Command;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class ListChapterCommand extends Command {

    public static final String COMMAND_WORD = "listchapter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of chapters available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.isAdminLevel() || access.isChapterLevel()) {
            System.out.println("Sorry, you currently are in the admin/chapter level, "
                    + "please go to module level first.");
            return;
        }

        Module newModule = access.getModule();
        ChapterList chapters = newModule.getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        int chapterCount = chapters.getChapterCount();
        ui.showChapterList(allChapters, chapterCount);
        access.setModule(newModule);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

