package seedu.hdbuy.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.common.exception.EmptyInputException;
import seedu.hdbuy.common.exception.EmptyParameterException;
import seedu.hdbuy.common.exception.GatewayException;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.InvalidInputException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.common.exception.MissingFileException;
import seedu.hdbuy.common.exception.NoFlatsException;
import seedu.hdbuy.common.exception.NoSearchException;
import seedu.hdbuy.parser.CommandType;

public class TextUi {

    /**
     * Number of dashes for the separator.
     */
    private static final int SEPARATOR_LENGTH = 80;
    private static final Scanner in = new Scanner(System.in);

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

    public static String readCommand() {
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        return CommandType.EXIT;
    }

    public static void showHelp() {
        System.out.print(
                "HdBuy is a way to easily find and bookmark resale flats of your liking.\n\n"
                + "Report bugs to: hdbuy@gmail.com\n" + "GitHub page: <https://github.com/AY2021S2-CS2113-F10-1/tp>\n"
                + "User Guide: <https://github.com/AY2021S2-CS2113-F10-1/tp/blob/master/docs/UserGuide.md>\n\n"
                + "Available commands:\n===============================================\n");
        Object[] summary = {"filter <attribute> <value>", "Add a filter condition. eg: filter location woodlands"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"list", "Show all currently set filter condition to filter units matching preferences"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"clear", "Remove all currently set filter conditions"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"find", "Search for units with the current filter conditions"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"sort <direction>", "Sort search results in ascending(asc) or descending(desc) order"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"save <index>", "Add the unit at the inputted index to the shortlist"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"remove <index>", "Remove the unit at the inpuuted index from the shortlist"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"shortlist", "Show all units in the shortlist"};
        System.out.format("%30s%80s\n", summary);
        summary = new Object[]{"exit", "Exit the application"};
        System.out.format("%30s%80s\n", summary);
    }

    public static void showParameters(LinkedHashMap<QueryKey, String> inputs) {
        if (inputs.isEmpty()) {
            System.out.println("Currently there are no filter conditions set.");
            return;
        }
        System.out.print("Filter conditions:\n" + inputs + "\n");
    }

    public static void showInvalidIndex(InvalidIndexException e) {
        System.out.println(e.getMessage());
    }

    public static void showRemovedShortlistUnit(String unitDescription) {
        System.out.println("You have removed unit from shortlist: " + unitDescription);
    }

    public static void showSavedShortlistUnit(String unitDescription) {
        System.out.println("You have saved unit to shortlist: " + unitDescription);
    }

    public static void showInvalidParameter(String key, InvalidParameterException e) {
        System.out.println(e.getMessage());
        switch (key) {
        case CommandType.FILTER:
            System.out.println("FILTER command needs a type and a parameter to work.");
            System.out.println("Filter types: " + Arrays.asList(QueryKey.values()));
            System.out.println("Example: \"filter location clementi\"");
            System.out.println("Example: \"filter type 4 room\", can be any of X room (X = 1 - 5) or executive");
            System.out.println("Example: \"filter lease_remaining 90\", can be any whole number from 0 - 99");
            break;
        case CommandType.FIND:
            System.out.println("FIND command does not need any parameters.");
            System.out.println("However, you need to provide filter before you execute the FIND command.");
            break;
        case CommandType.EXIT:
            System.out.println("EXIT command does not need any parameters.");
            System.out.println("You are closing the app after all.");
            break;
        case CommandType.HELP:
            System.out.println("HELP command does not need any parameters.");
            System.out.println("It's to help you understand all of our commands.");
            break;
        case CommandType.LIST:
            System.out.println("LIST command does not need any parameters.");
            System.out.println("The app will list out the parameters you have set.");
            break;
        case CommandType.REMOVE:
            System.out.println("REMOVE command needs a parameter to work.");
            System.out.println("Example: \"remove 1\"");
            break;
        case CommandType.SAVE:
            System.out.println("SAVE command needs a parameter to work.");
            System.out.println("Example: \"save 1\"");
            break;
        case CommandType.SHORTLIST:
            System.out.println("SHORTLIST command does not need any parameters.");
            System.out.println("If you want to modify the short list, use SAVE command or REMOVE command instead.");
            break;
        case CommandType.SORT:
            System.out.println("SORT command needs a parameter to work.");
            System.out.println("Sort types: {asc, desc}");
            System.out.println("Example: \"sort asc\"");
            break;
        case CommandType.CLEAR:
            System.out.println("CLEAR command does not need any parameters.");
            System.out.println("The purpose is to clear the filters inside the query!");
            break;
        default:
            break;
        }
    }

    public static void showUnits(ArrayList<Unit> units) {
        Object[] columnNames = {"Index", "Address", "Type", "Lease", "Price"};
        System.out.format("%5s%24s%12s%24s%12s\n", columnNames);
        int i = 0;
        for (Unit unit : units) {
            Object[] unitData = {++i, unit.getAddress(), unit.getType(), unit.getLease(), "$" + unit.getPrice()};
            System.out.format("%5s%24s%12s%24s%12s\n", unitData);
        }
    }

    public static void showShortListUnits(ArrayList<Unit> units) {
        Object[] columnNames = {"Index", "Address", "Type", "Lease", "Price"};
        System.out.format("%5s%24s%12s%24s%12s\n", columnNames);
        int i = 0;
        for (Unit unit : units) {
            Object[] unitData = {++i, unit.getAddress(), unit.getType(), unit.getLease(), "$" + unit.getPrice()};
            System.out.format("%5s%24s%12s%24s%12s\n", unitData);
        }
    }

    public static void showNoFlats(NoFlatsException e) {
        System.out.println(e.getMessage());
    }

    public static void showEmptyParameter(EmptyParameterException e) {
        System.out.println("\"FIND\"" + e.getMessage());
        System.out.println("Please specify a filter to use before executing this command.");
        System.out.println("Filters available: " + Arrays.asList(QueryKey.values()));
        System.out.println("An example will be \"filter location clementi\"");
    }

    public static void showClearedFilterConditions() {
        System.out.print("Cleared filter conditions.\n");
    }

    public static void showDuplicateUnit(DuplicateUnitException e) {
        System.out.println(e.getMessage());
    }

    public static void showDoSearchPrompt(NoSearchException e) {
        System.out.println(e.getMessage());
    }

    public static void showMissingFileError(MissingFileException e) {
        System.out.println(e.getMessage());
    }

    public static void showEmptyInputError(EmptyInputException e) {
        System.out.println(e.getMessage());
    }

    public static void showInvalidInputError(InvalidInputException e) {
        System.out.println(e.getMessage());
    }

    public static void showGateWayError(GatewayException e) {
        System.out.println(e.getMessage());
    }
}
