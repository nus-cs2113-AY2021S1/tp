package commands.add;

import access.Access;
import commands.Command;
import manager.admin.Admin;
import manager.admin.ModuleList;
import manager.chapter.CardList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addmodule";
    private final Module module;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new module. \n"
            + "Parameters: MODULE_NAME\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n";

    public AddModuleCommand(String moduleCode) {
        this.module = new Module(moduleCode);
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.isModuleLevel() || access.isChapterLevel()) {
            System.out.println("Sorry, you currently are in the module/chapter level, "
                    + "please go to admin level first.");
            return;
        }

        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        modules.addModule(module);
        int moduleCount = modules.getModuleCount();
        ui.showModuleAdded(module, moduleCount);
        access.setAdmin(newAdmin);
        storage.createModule(module.getModuleName());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
