package commands;

import access.Access;
import manager.admin.ModuleList;
import manager.chapter.Chapter;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GoModuleCommand extends GoCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n";

    private String module;

    public GoModuleCommand(String module) {
        this.module = module;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = goModule(access, storage);
        if (result.equals("")) {
            return;
        }
        ui.showToUser(result);
    }

    private String goModule(Access access, Storage storage) throws IOException {
        boolean isLevelExist = false;
        String result = "";
        String currentModule = this.module;
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        for (Module module : allModules) {
            String moduleName = module.getModuleName();
            if (currentModule.equalsIgnoreCase(moduleName)) {
                access.setModuleLevel(moduleName);
                isLevelExist = true;
                try {
                    ArrayList<Chapter> chapters = storage.loadChapter(module.getModuleName());
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
