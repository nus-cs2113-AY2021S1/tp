package commands.list;

import access.Access;
import commands.Command;
import manager.admin.Admin;
import manager.admin.ModuleList;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class ListModuleCommand extends Command {
    public static final String COMMAND_WORD = "listmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of modules available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.isModuleLevel() || access.isChapterLevel()) {
            System.out.println("Sorry, you currently are in the module/chapter level, "
                    + "please go to admin level first.");
            return;
        }

        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        int chapterCount = modules.getModuleCount();
        ui.showModuleList(allModules, chapterCount);
        access.setAdmin(newAdmin);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

