package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.admin.ModuleList;
import manager.chapter.Chapter;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GoModuleCommand extends GoCommand {
    private String module;

    public GoModuleCommand(String module) {
        this.module = module;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        String result = goModule(access, storage, ui);
        if (result.equals("")) {
            return;
        }
        ui.showToUser(result);
    }

    private String goModule(Access access, Storage storage, Ui ui) throws IncorrectAccessLevelException {
        boolean isLevelExist = false;
        String result = "";
        String currentModule = this.module;
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        for (Module module : allModules) {
            if (currentModule.equalsIgnoreCase(module.getModuleName())) {
                access.setModuleLevel(currentModule);
                isLevelExist = true;
                try {
                    ArrayList<Chapter> chapters = storage.loadChapter(module.getModuleName(), ui);
                    if (chapters.size() == 0) {
                        result = "This is a new module, you can try to add chapters inside!";
                    }
                    module.setChapters(chapters);
                    access.setModule(module);
                } catch (FileNotFoundException e) {
                    result = "The module folder cannot be found.";
                }
                break;
            }
        }
        if (!isLevelExist) {
            result = "Sorry, I cannot find this module, please add this module first";
        }
        return result;
    }
}
