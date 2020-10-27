package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import storage.Storage;
import ui.Ui;

public class BackModuleCommand extends BackCommand {

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        access.setModuleLevel("");
    }
}
