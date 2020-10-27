package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import manager.admin.Admin;
import manager.admin.ModuleList;
import manager.chapter.Chapter;
import manager.module.Module;
import storage.Storage;
import ui.Ui;


import java.io.IOException;

import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MODULE;
import static common.Messages.ADMIN;

public class AddModuleCommand extends AddCommand {
    public static final String ACCESS_LEVEL = ADMIN;
    public static final String MODULE_PARAMETERS = " MODULE_NAME";
    public static final String MODULE_MESSAGE_USAGE = COMMAND_WORD + ": Adds a new module. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " CS2113T\n";


    private String module;

    public AddModuleCommand(String module) {
        this.module = module;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        String result = "";

        if (access.isAdminLevel()) {
            Module module = new Module(this.module);
            result = addModule(access, storage, module);
        } else {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    access.getLevel(), ACCESS_LEVEL));
        }
        ui.showToUser(result);
    }

    private String addModule(Access access, Storage storage, Module module) {
        Admin newAdmin = access.getAdmin();
        ModuleList modules = newAdmin.getModules();
        modules.addModule(module);
        int moduleCount = modules.getModuleCount();
        access.setAdmin(newAdmin);
        storage.createModule(module.getModuleName());
        return prepareResult(MODULE, module.toString(), moduleCount);
    }

}
