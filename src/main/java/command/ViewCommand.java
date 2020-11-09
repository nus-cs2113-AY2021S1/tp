package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;


/**
 * Views the content of a specific cheatsheet.
 */
public class ViewCommand extends FinderCommand {
    public static final String invoker = "/view";

    /**
     * Constructor for ViewCommand.
     * Required flag: Either NAME or INDEX.
     *
     * @param printer The printer object handles the user interaction
     * @param cheatSheetList The cheatSheetList object holds the current list of cheatsheets
     */
    public ViewCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeFlags.add(CommandFlag.NAME);
        alternativeFlags.add(CommandFlag.INDEX);
    }

    /**
     * Gets a cheatsheet from cheatSheetList according to name or index, whichever the user entered.
     * Content of the cheatsheet would be printed out on the terminal.
     *
     * @throws CommandException Thrown if the name or index entered is invalid
     */
    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet desiredCheatSheet = getCheatSheetFromNameOrIndex();
            printer.printViewCheatSheetMessage(desiredCheatSheet);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException("Please enter a valid name or/and index");
        }
    }
}
