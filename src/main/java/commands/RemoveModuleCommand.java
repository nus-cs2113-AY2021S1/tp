package commands;

import access.Access;
import common.KajiLog;
import manager.admin.ModuleList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MODULE;

//@@author neojiaern
/**
 * Removes a module from Kaji.
 */
public class RemoveModuleCommand extends RemoveCommand {
    private static Logger logger = KajiLog.getLogger(RemoveModuleCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes module based on the index in the list. \n"
            + "Parameters: " + MODULE_PARAMETER + "\n" + "Example: " + COMMAND_WORD + " 2\n";

    private final int removeIndex;

    public RemoveModuleCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    /**
     * Executes the RemoveModuleCommand by calling the relevant methods.
     *
     * @param ui ui which the command uses to print messages
     * @param access access which the command uses to get the modules
     * @param storage storage which the command uses to load or write data to storage files
     * @throws IOException if there is error in loading or writing data to storage files
     */
    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = removeModule(access, storage);
        ui.showToUser(result);
    }

    /**
     * Removes a module from Kaji.
     *
     * @param access to get the list of module objects
     * @param storage to remove module from storage
     * @return result of removing the module
     * @throws IOException if there is an error removing the module
     */
    private String removeModule(Access access, Storage storage) throws IOException {
        assert access.isAdminLevel() : "Not admin level";
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
            allModules.remove(removeIndex);
            logger.info("Module: " + module.toString() + " successfully deleted.");
            return prepareResult(MODULE, module.toString(), allModules.size());
        } catch (IndexOutOfBoundsException e) {
            String result = String.format(MESSAGE_INVALID_INDEX_RANGE, MODULE);
            logger.info(result);
            return result;
        }
    }
}
