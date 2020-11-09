package command;

import cheatsheet.CheatSheetList;

import exception.CommandException;

import sort.SortByName;
import ui.Printer;
import ui.TablePrinter;

/**
 * Command to list all cheatsheets in CheatSheetList in a table format (Index, Name, Subject, Preview).
 */
public class ListCommand extends Command {
    public static final String invoker = "/list";
    private CheatSheetList cheatSheetList;

    /**
     * Constructor for ListCommand.
     *
     * @param printer The printer object handles the user interaction
     * @param cheatSheetList The cheatSheetList object holds the current list of cheatsheets
     */
    public ListCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
    }

    /**
     * Prints a table with all cheatsheets in cheatSheetList
     * Header of each column in table: Index, Name, Subject, Preview
     * If there is cheatsheet in the list, the program enters sorting mode that allows user to sort by name or subject.
     *
     * @throws CommandException Thrown if no cheatsheets are found in cheatSheetList
     */
    @Override
    public void execute() throws CommandException {
        cheatSheetList.getList().sort(new SortByName());

        if (cheatSheetList.getSize() == 0) {
            throw new CommandException("You don't have any cheat sheet. Use the /add command to create a new one");
        }

        TablePrinter tp = new TablePrinter(printer, cheatSheetList);
        tp.execute();

        SortFilter sortFilter = new SortFilter(cheatSheetList.getList(), printer);
        sortFilter.execute();
    }
}
