package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

public class BackAdminCommand extends BackCommand {

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        access.setModuleLevel("");
    }
}
