package seedu.hdbuy.ui;

import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.EmptyParameterException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.parser.CommandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class TextUi {

    /**
     * Number of dashes for the separator.
     */
    private static final int SEPARATOR_LENGTH = 80;
    private static final Scanner in = new Scanner(System.in);

    private static final String unitFormat = "%-8s%-24s%-12s%-24s%-12s%n";
    private static final String helpFormat = "%-30s%-80s%n";

    /**
     * Returns a String input if the user has input.
     * If the user doesn't have any input, the app will return an "EXIT" string.
     *
     * @return the string of the user input or "exit"
     */
    public static String readCommand() {
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        return CommandType.EXIT;
    }

    /**
     * Returns a separator when showing messages, so that user can easily differentiate each command they entered.
     */
    public static void showSeparator() {
        for (int i = 0; i < SEPARATOR_LENGTH; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    /**
     * Shows a welcome message when the user starts up the app.
     */
    public static void showWelcome() {
        showSeparator();
        System.out.print("Welcome to HDBuy!\nHow may I help you today?\n");
        showSeparator();
    }

    /**
     * Shows an exit message when the user exits the app.
     */
    public static void showExit() {
        System.out.print("HDBuy Bye Bye!\n");
        in.close();
    }

    /**
     * Shows all the commands available and their usages alongside with their definitions.
     * The message will be shown in a table.
     */
    public static void showHelp() {
        System.out.print("HdBuy is a way to easily find and bookmark resale flats of your liking.\n\n"
            + "Report bugs to: hdbuy@gmail.com\n" + "GitHub page: <https://github.com/AY2021S2-CS2113-F10-1/HdBuy>\n"
            + "User Guide: <https://ay2021s2-cs2113-f10-1.github.io/HdBuy/UserGuide.html>\n"
            + "The app is meant to recognise commands in `small caps`. Ensure caps lock is disabled.\n"
            + "Available commands:\n========================================"
            + "========================================\n");

        String[] terms = {"filter <attribute> <value>", "list", "clear", "find", "sort <order>", "save <index>",
            "remove <index>", "shortlist", "help", "exit"};

        for (String term : terms) {
            Object[] summary = {term, TermDefinition.getDefinition(term)};
            System.out.format(helpFormat, summary);
        }
    }

    /**
     * Shows the error messages when the user tries to initiate a search without specifying the filters.
     *
     * @param inputs [Placeholder]
     */
    public static void showParameters(LinkedHashMap<QueryKey, String> inputs) {
        if (inputs.isEmpty()) {
            System.out.println("Currently there are no filter conditions set.");
            return;
        }
        System.out.print("Filter conditions:\n" + inputs + "\n");
    }

    /**
     * This method will show the correct format of using the specific command by providing an example.
     *
     * @param key The valid Command argument that can be used by the user.
     * @param e   The error message triggered by the wrong command format.
     */
    public static void showInvalidParameter(String key, InvalidParameterException e) {
        System.out.println(e.getMessage());
        System.out.println(TermDefinition.getExample(key));
    }

    /**
     * This method will show all the units to user.
     *
     * @param units The units that fulfilled the conditions set by the user.
     */
    public static void showUnits(ArrayList<Unit> units) {
        Object[] columnNames = {"Index", "Address", "Type", "Lease", "Price"};
        System.out.format(unitFormat, columnNames);
        int i = 0;
        for (Unit unit : units) {
            Object[] unitData = {++i, unit.getAddress(), unit.getType(), unit.getLease(), "$" + unit.getPrice()};
            System.out.format(unitFormat, unitData);
        }
    }

    /**
     * This method will display an error message if the user initiate the search without providing any filters.
     *
     * @param e The exception triggered by not providing filter before initializing search.
     */
    public static void showEmptyParameter(EmptyParameterException e) {
        System.out.println(e.getMessage());
        System.out.println("Please specify a filter to use before executing this command.");
        System.out.println("Filters available: " + Arrays.asList(QueryKey.values()));
        System.out.println("An example will be \"filter location clementi\"");
    }

    /**
     * Shows a message after user clears out all the filters.
     */
    public static void showClearedFilterConditions() {
        System.out.print("Cleared filter conditions.\n");
    }

    /**
     * Shows the unit the user has requested to remove from their shortlist.
     *
     * @param unitDescription The description of the unit that is requested to be removed by the user.
     */
    public static void showRemovedShortlistUnit(String unitDescription) {
        System.out.println("You have removed unit from shortlist: " + unitDescription);
    }

    /**
     * Shows a message after the user requested a unit to be saved into the shortlist.
     *
     * @param unitDescription The description of the unit that is requested to be saved by the user.
     */
    public static void showSavedShortlistUnit(String unitDescription) {
        System.out.println("You have saved unit to shortlist: " + unitDescription);
    }

    /**
     * Default exception message to be shown if an exception is caught.
     */
    public static void showExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
