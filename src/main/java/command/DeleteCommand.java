package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import storage.DataFileDestroyer;
import ui.Printer;
import ui.Ui;

/**
 * Command to delete a cheat sheet in the list of cheatsheets.
 */
public class DeleteCommand extends FinderCommand {
    protected DataFileDestroyer fileDestroyer;
    protected Ui ui;

    public static final String invoker = "/delete";

    /**
     * Constructor for the DeleteCommand.
     * Required flag: either NAME or INDEX.
     *
     * @param printer        The printer object to handle user interaction
     * @param cheatSheetList The current list of cheatsheets
     * @param fileDestroyer  The DataFileDestroyer object to remove the cheat sheet from the /data folder
     */
    public DeleteCommand(Printer printer, CheatSheetList cheatSheetList, DataFileDestroyer fileDestroyer, Ui ui) {
        super(printer, cheatSheetList);
        this.fileDestroyer = fileDestroyer;
        this.ui = ui;

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeFlags.add(CommandFlag.NAME);
        alternativeFlags.add(CommandFlag.INDEX);
    }

    /**
     * Asks the user to confirm the clearing of cheatsheets, then
     * Deletes the cheat sheet from the list and delete the corresponding
     * file from the /data folder.
     */
    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet cheatSheetToDelete = getCheatSheetFromNameOrIndex();
            printer.printDeleteConfirmation(cheatSheetToDelete);
            if (isDeleteConfirmed()) {
                fileDestroyer.executeFunction(cheatSheetToDelete.getName());
                cheatSheetList.remove(cheatSheetToDelete.getName());
                printer.printDeleteCheatSheetMessage(cheatSheetToDelete, cheatSheetList);
            }
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException("Please enter a valid name or/and index");
        }
    }

    /**
     * Asks for user confirmation of operation. Then verifies
     * that the user correctly confirmed that all cheatsheets should be cleared
     *
     * @return      true if the user has confirmed correctly else false
     */
    private boolean isDeleteConfirmed() {
        String userAnswer = ui.getUserInput();
        userAnswer = userAnswer.toLowerCase();
        return userAnswer.equals("yes") || userAnswer.equals("y");
    }

}
