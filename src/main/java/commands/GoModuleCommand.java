package commands;

import access.Access;
import manager.admin.ModuleList;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GoModuleCommand extends Command {
    public static final String COMMAND_WORD = "gomodule";
    String moduleCode;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to module level. \n"
            + "Example: " + COMMAND_WORD + "\n";

    public GoModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        boolean isLevelExist = false;
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        for (Module module : allModules) {
            if (moduleCode.equalsIgnoreCase(module.getModuleName())) {
                access.setModuleLevel(moduleCode);
                isLevelExist = true;
                try {
                    ArrayList<Chapter> chapters = storage.loadChapter(module.getModuleName());
                    if (chapters.size() == 0) {
                        System.out.println("This is a new module, you can try to add chapters inside!");
                    }
                    module.setChapters(chapters);
                    access.setModule(module);
                } catch (FileNotFoundException e) {
                    System.out.println("The module folder cannot be found.");
                }
                break;
            }
        }
        if (!isLevelExist) {
            System.out.println("Sorry, I cannot find this module, please add this module first");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
