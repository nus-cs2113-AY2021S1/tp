package athena.exceptions.command;

import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;

public class InvalidTimeFormatException extends CommandException {
    public InvalidTimeFormatException() {

    }

    /**
     * Print error message telling user they entered time in invalid format.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidTimeFormatException();
    }


}
