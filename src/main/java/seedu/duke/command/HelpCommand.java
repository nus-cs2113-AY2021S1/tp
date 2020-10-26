package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command{

    ArrayList<String> helpStuff;

    public HelpCommand() {
        this.isExit = false;
        this.command = null;
        helpStuff = new ArrayList<>();

    }

    public void execute(UserData data, Ui ui, Storage storage) {
        storage.loadSystemResources("/helpfile.txt", helpStuff);
        for (String line : helpStuff) {
            System.out.println(line);
        }
    }
}
