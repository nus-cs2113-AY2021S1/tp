package commands;

import access.Access;
import exception.EmptyFileException;
import manager.admin.ModuleList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

import static common.Messages.MODULE;

public class ListModulesCommand extends ListCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of modules available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws EmptyFileException {
        String result = listModules(access);
        ui.showToUser(result);
    }

    private String listModules(Access access) throws EmptyFileException {
        assert access.isAdminLevel() : "Not admin level";
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        int moduleCount = modules.getModuleCount();

        if (moduleCount == 0) {
            throw new EmptyFileException(String.format(MESSAGE_DOES_NOT_EXIST, MODULE));
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_EXIST, MODULE));
        for (Module m : allModules) {
            result.append("\n").append(allModules.indexOf(m) + 1).append(".").append(m);
        }
        return result.toString();
    }
}
