package commands;

import access.Access;
import manager.chapter.CardList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class goModuleCommand extends Command {
    public static final String COMMAND_WORD = "gomodule";
    String moduleCode;

    public goModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        boolean isLevelExist = false;
        ArrayList<Module> modules = access.getAdmin().getModules();
        for (Module module : modules) {
            if(moduleCode.equalsIgnoreCase(module.getModule())) {
                access.setModuleLevel(moduleCode);
                isLevelExist = true;
                try {
                    Module newModule = new Module(module.getModule(), storage.loadChapter(module.getModule()));
                    access.setModule(newModule);
                } catch (FileNotFoundException e) {
                    Module newModule = new Module(module.getModule());
                    access.setModule(newModule);
                    System.out.println("Hihi, seems like it is a new module, you can try to add chapter inside!");
                }
                break;
            }
        }
        if (isLevelExist == false) {
            System.out.println("Sorry, I cannot find this module, please add this module first");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
