package commands;

import access.Access;
import common.KajiLog;
import exception.StorageDataException;
import exception.InvalidInputException;
import manager.admin.ModuleList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.util.logging.Logger;

import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MESSAGE_ITEM_EXISTED;
import static common.Messages.MODULE;

/**
 * Edits the module name.
 */
public class EditModuleCommand extends EditCommand {
    private static Logger logger = KajiLog.getLogger(EditModuleCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the module name.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1 CS2113T\n";

    public static final String MESSAGE_SAME_NAME = "%1$s has the same module name as what you entered: %2$s\n";

    private final int editIndex;
    private String module;

    /**
     * Creates an EditModuleCommand to edit to the specified {@code module}.
     *
     * @param editIndex of the module in the list of modules to edit
     * @param module name to edit the module to
     */
    public EditModuleCommand(int editIndex, String module) {
        this.editIndex = editIndex;
        this.module = module;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, StorageDataException {
        String result = editModule(access, storage);
        ui.showToUser(result);
    }

    /**
     * Edits the name of the module.
     *
     * @param access to get the list of modules
     * @param storage to rename the storage file
     * @return result to be displayed
     * @throws InvalidInputException if the index is invalid
     * @throws StorageDataException if there is an error renaming the storage file
     */
    private String editModule(Access access, Storage storage) throws InvalidInputException, StorageDataException {
        assert access.isAdminLevel() : "Not admin level";
        assert !module.isEmpty() : "The module name is missing.";
        ModuleList modules = access.getAdmin().getModules();
        try {
            Module module = modules.getModule(editIndex);

            if (this.module.equals(module.getModuleName())) {
                return String.format(MESSAGE_SAME_NAME, module.getModuleName(), this.module);
            }
            if (modules.checkModuleExistence(this.module.toLowerCase())
                    && !this.module.toLowerCase().equals(module.getModuleName().toLowerCase())) {
                return String.format(MESSAGE_ITEM_EXISTED, MODULE, this.module, MODULE);
            }

            logger.info("Renaming module: " + module);
            storage.renameModule(this.module, module);
            StringBuilder result = new StringBuilder();
            result.append(prepareBeforeEdit(MODULE, module.getModuleName()));
            module.setModuleName(this.module);
            logger.info("Module successfully renamed to: " + module);
            result.append(prepareAfterEdit(MODULE, module.getModuleName()));
            return result.toString();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_INDEX_RANGE, MODULE));
        }
    }
}
