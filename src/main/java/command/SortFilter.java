package command;

import cheatsheet.CheatSheet;
import exception.CommandException;
import sort.SortBySubject;
import sort.SortBySubjectRev;
import sort.SortByName;
import sort.SortByNameRev;
import ui.ConsoleColorsEnum;
import ui.Printer;
import ui.TablePrinter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Complementary command for findCommand and ListCommand to sort cheatsheets in CheatSheetList.
 */
public class SortFilter {
    final String promptSortConfig = ConsoleColorsEnum.RED_TEXT
        + "Sort filter (1: name ascending, 2: subject ascending, 3: name descending" + ", 4: "
        + "subject descending, ANY OTHER CHAR: EXIT)" + ConsoleColorsEnum.RESET_TEXT;
    Printer printer;
    ArrayList<CheatSheet> cheatSheetArrayList;

    /**
     * Constructor for SortFilter.
     *
     * @param cheatSheetArrayList The cheatSheetArrayList object holds the current list of cheatsheets in an
     *                            ArrayList type instead of CheatSheetList type
     * @param printer The printer object handles the user interaction
     */
    public SortFilter(ArrayList<CheatSheet> cheatSheetArrayList, Printer printer) {
        this.cheatSheetArrayList = cheatSheetArrayList;
        this.printer = printer;
    }

    /**
     * Asks for user input on the mode of sorting then sorts accordingly.
     * The sorted cheatsheets would be printed in a table.
     * Format of the printed table is reused from a previous table has already been created.
     *
     * @param tp The tp object contains the structure and format of the printed table in ListCommand and FindCommand
     * @throws CommandException Thrown if user enters any characters which is not [1-4]
     */
    public void execute(TablePrinter tp) throws CommandException {
        do {
            askForInput();
            tp.execute();
        } while (true);
    }

    /**
     * Asks for user input on the mode of sorting then sorts accordingly.
     * The sorted cheatsheets would be printed in a table.
     * Format of the printed table re-created every time the method is called.
     *
     * @throws CommandException Thrown if user enters any characters which is not [1-4]
     */
    public void execute() throws CommandException {
        do {
            askForInput();
            TablePrinter tp = new TablePrinter(printer, cheatSheetArrayList);
            tp.execute();
        } while (true);
    }

    /**
     * Asks for user input and sorts the table accordingly.
     *
     * @throws CommandException Thrown if user enters any characters which is not [1-4]
     */
    private void askForInput() throws CommandException {
        printer.print(promptSortConfig);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
        case "1":
            cheatSheetArrayList.sort(new SortByName());
            printer.print("Sorted name in ascending order");
            break;
        case "2":
            cheatSheetArrayList.sort(new SortBySubject());
            printer.print("Sorted subject in ascending order");
            break;
        case "3":
            cheatSheetArrayList.sort(new SortByNameRev());
            printer.print("Sorted name in descending order");
            break;
        case "4":
            cheatSheetArrayList.sort(new SortBySubjectRev());
            printer.print("Sorted subject in descending order");
            break;
        default:
            throw new CommandException("Exited list command");
        }
    }
}
