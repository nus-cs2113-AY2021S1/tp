package commands;

import access.Access;
import exception.DuplicateDataException;
import exception.InvalidInputException;
import manager.admin.ModuleList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MODULE;

public class EditModuleCommand extends EditCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the module name.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1 CS2113T\n";

    public static final String MESSAGE_SAME_NAME = "%1$s has the same module name as what you entered: %2$s\n";

    private final int editIndex;
    private String module;

    public EditModuleCommand(int editIndex, String module) {
        this.editIndex = editIndex;
        this.module = module;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws InvalidInputException, DuplicateDataException {
        String result = editModule(access, storage);
        ui.showToUser(result);
    }

    private String editModule(Access access, Storage storage) throws InvalidInputException, DuplicateDataException {
        assert access.isAdminLevel() : "Not admin level";
        assert !module.isEmpty() : "The module name is missing.";
        ModuleList modules = access.getAdmin().getModules();
        try {
            Module module = modules.getModule(editIndex);

            if (this.module.equals(module.getModuleName())) {
                return String.format(MESSAGE_SAME_NAME, module.getModuleName(), this.module);
            }

            storage.renameModule(this.module, module);
            StringBuilder result = new StringBuilder();
            result.append(prepareBeforeEdit(MODULE, module.getModuleName()));
            module.setModuleName(this.module);
            result.append(prepareAfterEdit(MODULE, module.getModuleName()));
            return result.toString();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_INDEX_RANGE, MODULE));
        }
    }
}
