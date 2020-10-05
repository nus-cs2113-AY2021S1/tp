package seedu.duke.tool;

import seedu.duke.command.*;

public class Parser {

    /**
     * Select the corresponding Command class through interpreting the command entered by user.
     * Returns the correct Command class.
     *
     * @param fullCommand Command entered by user.
     * @return Construct new Command that is corresponding to the command entered by user.
     */
    public static Command parse(String fullCommand) {
        if(fullCommand.toLowerCase().contains("addmodule")) {
            return new addModuleCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("addchapter")) {
            return new addChapterCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("addcard")) {
            return new addCardCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("gomodule")) {
            return new goModuleCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("backmodule")) {
            return new backModuleCommand(fullCommand);
        }
        return null;
    }
}