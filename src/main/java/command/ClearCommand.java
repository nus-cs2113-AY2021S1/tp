package command;

import cheatsheet.CheatSheetList;
import storage.DataFileDestroyer;
import storage.DataFileReader;
import ui.Printer;
import ui.Ui;

/**
 * Command to remove all cheatsheets from CheatSheetList.
 */
public class ClearCommand extends Command {
    protected DataFileDestroyer fileDestroyer;
    protected DataFileReader fileReader;
    protected Ui ui;

    public static final String invoker = "/clear";

    /**
     * Constructor for the ClearCommand.
     * No command flag is required.
     *
     * @param printer        The printer object to handle user interaction
     * @param cheatSheetList The current list of cheat sheets
     * @param fileDestroyer  The fileDestroyer object removes cheat sheet file from the /data folder
     */
    public ClearCommand(Printer printer, CheatSheetList cheatSheetList, DataFileDestroyer fileDestroyer,
                       DataFileReader fileReader, Ui ui) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
        this.fileDestroyer = fileDestroyer;
        this.fileReader = fileReader;
        this.ui = ui;
    }

    /**
     * Asks the user to confirm the clearing of cheatsheets, then
     * Removes all cheatsheets from cheatSheetList object.
     * Removes all cheatsheet files from /data folder.
     * Prints a confirmation that cheatsheets have been deleted.
     */
    @Override
    public void execute() {
        printer.printClearConfirmation();
        if (isClearConfirmed()) {
            final int originalCheatSheets = cheatSheetList.getSize();

            fileDestroyer.executeFunction();
            cheatSheetList.clear();

            fileReader.executeFunction();
            printer.printClearCheatSheetMessage(originalCheatSheets
                    - cheatSheetList.getSize());
        }
    }

    /**
     * Asks for user confirmation of operation. Then verifies that the user correctly
     * confirmed that cheatsheet should be deleted
     *
     * @return      true if the user has confirmed correctly else false
     */
    private boolean isClearConfirmed() {
        String userAnswer = ui.getUserInput();
        userAnswer = userAnswer.toLowerCase();
        return userAnswer.equals("yes") || userAnswer.equals("y");
    }
}
