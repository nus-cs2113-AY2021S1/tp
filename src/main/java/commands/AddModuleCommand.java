package commands;

import access.Access;
import manager.admin.Admin;
import manager.chapter.CardList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addmodule";
    String moduleCode;

    public AddModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) {
        if (access.getModuleLevel() == "") {
            Admin newAdmin = access.getAdmin();
            newAdmin.add(new Module(moduleCode));
            access.setAdmin(newAdmin);
            storage.createModule(moduleCode);
        } else {
            System.out.println("Sorry, you currently are in the module/chapter level, "
                    + "please go back to Admin level first.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
