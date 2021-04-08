package seedu.hdbuy.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.EmptyParameterException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.parser.CommandType;

public class TextUi {

    private static final String REPORTING_EMAIL = "hdbuy@gmail.com";
    private static final String GITHUB_PAGE = "https://github.com/AY2021S2-CS2113-F10-1/tp";
    private static final String UG_PAGE = "https://github.com/AY2021S2-CS2113-F10-1/tp/blob/master/docs/UserGuide.md";

    /**
     * Number of dashes for the separator.
     */
    private static final int SEPARATOR_LENGTH = 100;
    private static final Object[] columnNames = {"Index", "Address", "Type", "Lease", "Price"};
    private static final String outputFormat = "%-7s%-24s%-12s%-24s%-12s%n";
    private static final String helpFormat = "%-30s%-80s%n";

    private static final Scanner in = new Scanner(System.in);

    public static String readCommand() {
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        return CommandType.EXIT;
    }

    public static void showSeparator() {
        for (int i = 0; i < SEPARATOR_LENGTH; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void showWelcome() {
        showSeparator();
        System.out.print("Welcome to HDBuy!\nHow may I help you today?\n");
        showSeparator();
    }

    public static void showExit() {
        System.out.print("HDBuy Bye Bye!\n");
        in.close();
    }

    public static void showHelp() {
        System.out.print(
                "HdBuy is a way to easily find and bookmark resale flats of your liking.\n\n"
                + "Report bugs to: " + REPORTING_EMAIL + "\n"
                + "GitHub page: <" + GITHUB_PAGE + ">\n"
                + "User Guide: <" + UG_PAGE + ">\n\n"
                + "Available commands:\n===============================================\n");
        ArrayList<Object[]> summaries = new ArrayList<>();
        summaries.add(new Object[]{"filter <attribute> <value>",
            "Add a filter condition. eg: filter location woodlands"});
        summaries.add(new Object[]{"list",
            "Show all currently set filter condition to filter units matching preferences"});
        summaries.add(new Object[]{"clear", "Remove all currently set filter conditions"});
        summaries.add(new Object[]{"find", "Search for units with the current filter conditions"});
        summaries.add(new Object[]{"sort <direction>",
            "Sort search results in ascending(asc) or descending(desc) order"});
        summaries.add(new Object[]{"save <index>", "Add the unit at the inputted index to the shortlist"});
        summaries.add(new Object[]{"remove <index>", "Remove the unit at the inputted index from the shortlist"});
        summaries.add(new Object[]{"shortlist", "Show all units in the shortlist"});
        summaries.add(new Object[]{"exit", "Exit the application"});
        for (Object[] summary : summaries) {
            System.out.format(helpFormat, summary);
        }
    }

    public static void showParameters(LinkedHashMap<QueryKey, String> inputs) {
        if (!inputs.isEmpty()) {
            System.out.print("Filter conditions:\n" + inputs + "\n");
        } else {
            System.out.println("Currently there are no filter conditions set.");
        }

    }

    public static void showInvalidParameter(String key, InvalidParameterException e) {
        System.out.println(e.getMessage());
        showInvalidParameterHint(key);
        switch (key) {
        case CommandType.FILTER:
            showFilterGuide();
            break;
        case CommandType.FIND:
            System.out.println("However, you need to provide filter before you execute the FIND command.");
            break;
        case CommandType.EXIT:
            System.out.println("You are closing the app after all.");
            break;
        case CommandType.HELP:
            System.out.println("It's to help you understand all of our commands.");
            break;
        case CommandType.LIST:
            System.out.println("The app will list out the parameters you have set.");
            break;
        case CommandType.REMOVE:
            System.out.println("Example: \"remove 1\"");
            break;
        case CommandType.SAVE:
            System.out.println("Example: \"save 1\"");
            break;
        case CommandType.SHORTLIST:
            System.out.println("If you want to modify the short list, use SAVE command or REMOVE command instead.");
            break;
        case CommandType.SORT:
            System.out.println("Sort types: {asc, desc}");
            System.out.println("Example: \"sort asc\"");
            break;
        case CommandType.CLEAR:
            System.out.println("The purpose is to clear the filters inside the query!");
            break;
        default:
            break;
        }
    }

    /**
     * Prints the hint to inform the user of the appropriate number of parameters required for that key command.
     * Example output: "FILTER command needs a type and a parameter to work."
     *
     * @param key The command the user is trying to execute.
     */
    public static void showInvalidParameterHint(String key) {
        System.out.print(key.toUpperCase() + " command ");
        switch (key) {
        case CommandType.FILTER:
            System.out.println("needs a type and a parameter to work.");
            break;
        case CommandType.REMOVE:
        case CommandType.SAVE:
        case CommandType.SORT:
            System.out.println("needs a parameter to work.");
            break;
        default:
            System.out.println("does not need any parameters.");
            break;
        }
    }

    public static void showUnits(ArrayList<Unit> units) {
        System.out.format(outputFormat, columnNames);
        int i = 0;
        for (Unit unit : units) {
            Object[] unitData = {++i, unit.getAddress(), unit.getType(), unit.getLease(), "$" + unit.getPrice()};
            System.out.format(outputFormat, unitData);
        }
    }

    public static void showEmptyParameter(EmptyParameterException e) {
        System.out.println("\"FIND\"" + e.getMessage());
        System.out.println("Please specify a filter to use before executing this command.");
        showFilterGuide();
    }

    public static void showClearedFilterConditions() {
        System.out.print("Cleared filter conditions.\n");
    }

    public static void showRemovedShortlistUnit(String unitDescription) {
        System.out.println("You have removed unit from shortlist: " + unitDescription);
    }

    public static void showSavedShortlistUnit(String unitDescription) {
        System.out.println("You have saved unit to shortlist: " + unitDescription);
    }

    public static void showExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void showFilterGuide() {
        System.out.println("Filters available: " + Arrays.asList(QueryKey.values()));
        System.out.println("Example: \"filter location clementi\"");
        System.out.println("Example: \"filter type 4 room\", can be any of X room (X = 1 - 5) or executive");
        System.out.println("Example: \"filter lease_remaining 90\", can be any whole number from 0 - 99");
    }
}
