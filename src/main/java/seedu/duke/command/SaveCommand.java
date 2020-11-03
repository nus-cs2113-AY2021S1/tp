package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class SaveCommand extends Command {

    public SaveCommand() {
        isExit = false;

    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        storage.saveAll(data);
        ui.printStorageSavedMessage();
    }
}
