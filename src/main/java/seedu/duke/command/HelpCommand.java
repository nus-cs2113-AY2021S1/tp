package seedu.duke.command;

import org.apache.commons.text.WordUtils;
import seedu.duke.data.UserData;
import seedu.duke.exception.InvalidHelpTopicException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;



public class HelpCommand extends Command {

    ArrayList<String> helpStuff;
    String helpTopic;
    WordUtils wrapper = new WordUtils();

    /**
     * Creates a help command object to be executed.
     *
     * @param argument is a String containing the user input
     */
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

    /**
     * Function will execute the command. Prints out the help information requested by user
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      object of UI class that contains the user interface needed to print the help file
     * @param storage Storage oject with the save file path to write to to save changes.
     * @throws InvalidHelpTopicException if there is no such help topic available
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws InvalidHelpTopicException {
        storage.loadSystemResources("/helpfile.txt", helpStuff);
        int beginIndex = searchIndex(helpStuff, true);
        int endIndex = searchIndex(helpStuff, false);

        if (beginIndex < 0 || endIndex < 0) {
            throw new InvalidHelpTopicException(this.helpTopic);
        }

        for (int i = beginIndex + 1; i < endIndex; i++) {
            String line = helpStuff.get(i);
            line = wrapper.wrap(line, 80, "\n", false);
            ui.printMessage(line);
        }

    }

    /**
     * Finds the line number of the start and end of the help topic located in the txt file.
     * @param helpData ArrayList of Strings containing all help file info loaded from the helpfile.txt
     * @param isBegin boolean Set to True to find the beginning of the help topic, set to false to find the end.
     * @return integer of line number of the help topic beginning or end
     */
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
