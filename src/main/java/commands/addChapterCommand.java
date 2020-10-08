package commands;

import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.Module;
import access.Access;
import storage.Storage;
import ui.Ui;

public class addChapterCommand extends Command {
    public static final String COMMAND_WORD = "addchapter";
    String chapterCode;

    public addChapterCommand(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if(access.getModuleLevel() != "") {
            Module newModule = access.getModule();
            newModule.add(new Chapter(chapterCode));
            access.setModule(newModule);
            storage.createChapter(chapterCode, access.getModuleLevel());
        }
        else {
            System.out.println("Sorry, you currently are in the admin level, please enter module level first.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
