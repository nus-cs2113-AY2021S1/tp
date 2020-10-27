package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.exception.InvalidHelpTopicException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command {

    ArrayList<String> helpStuff;
    String helpTopic;

    public HelpCommand(String argument) {
        this.isExit = false;
        this.command = null;
        argument = argument.trim();
        if (argument.equals("")) { //blank argument set to be summary
            argument = "summary";
        }
        this.helpTopic = argument;
        helpStuff = new ArrayList<>();

    }

    public void execute(UserData data, Ui ui, Storage storage) throws InvalidHelpTopicException {
        storage.loadSystemResources("/helpfile.txt", helpStuff);
        int beginIndex = searchIndex(helpStuff, true);
        int endIndex = searchIndex(helpStuff, false);

        if (beginIndex < 0 || endIndex < 0) {
            throw new InvalidHelpTopicException(this.helpTopic);
        }

        for (int i = beginIndex + 1; i < endIndex; i++) {
            String line = helpStuff.get(i);
            ui.printMessage(line);
        }

    }

    private int searchIndex(ArrayList<String> helpData, boolean isBegin) {

        String toSearchFor;
        if (isBegin) {
            toSearchFor = "begin " + helpTopic;
        } else {
            toSearchFor = "end " + helpTopic;
        }

        int index;
        index = helpData.indexOf(toSearchFor);
        return index;
    }
}
