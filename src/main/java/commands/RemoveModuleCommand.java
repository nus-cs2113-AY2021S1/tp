package commands;

import access.Access;
import common.KajiLog;
import exception.ExclusionFileException;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import manager.admin.ModuleList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RemoveModuleCommand extends RemoveCommand {
    private static Logger logger = KajiLog.getLogger(RemoveModuleCommand.class.getName());

    public static final String MESSAGE_SUCCESS_MODULE = "The module <%1$s> has been removed.\n";
    public static final String MESSAGE_REMAINING_MODULE = "You currently have %1$d module(s).";
    public static final String MESSAGE_INVALID_INDEX_MODULE = "The module is not found, please try again.";

    private final int removeIndex;

    public RemoveModuleCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = removeModule(access, storage);
        ui.showToUser(result);
    }

    private String removeModule(Access access, Storage storage) throws IOException {
        assert access.isAdminLevel() : "Not admin level";
        StringBuilder result = new StringBuilder();
        try {
            ModuleList modules = access.getAdmin().getModules();
            ArrayList<Module> allModules = modules.getAllModules();
            Module module = allModules.get(removeIndex);
            File directory = new File(storage.getFilePath() + "/" + module.toString());
            logger.info("Deleting module: " + module.toString());
            boolean isRemoved = storage.deleteDirectory(directory);
            if (!isRemoved) {
                throw new IOException("There was a problem deleting module in directory.");
            }
            result.append(String.format(MESSAGE_SUCCESS_MODULE, module.toString()));
            allModules.remove(removeIndex);
            result.append(String.format(MESSAGE_REMAINING_MODULE, allModules.size()));
        } catch (IndexOutOfBoundsException e) {
            logger.info(MESSAGE_INVALID_INDEX_MODULE);
            result.append(MESSAGE_INVALID_INDEX_MODULE);
        }
        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
