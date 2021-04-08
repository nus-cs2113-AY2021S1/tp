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
        System.out.print("HdBuy is a way to easily find and bookmark resale flats of your liking.\n\n"
            + "Report bugs to: hdbuy@gmail.com\n" + "GitHub page: <https://github.com/AY2021S2-CS2113-F10-1/tp>\n"
            + "User Guide: <https://github.com/AY2021S2-CS2113-F10-1/tp/blob/master/docs/UserGuide.md>\n\n"
            + "Available commands:\n===============================================\n");

        String[] terms = {"filter <attribute> <value>", "list", "clear", "find", "sort <direction>", "save <index>",
            "remove <index>", "shortlist", "exit"};

        for (String term : terms) {
            Object[] summary = {term, TermDefinition.getDefinition(term)};
            System.out.format("%30s%80s\n", summary);
        }
    }

    public static void showParameters(LinkedHashMap<QueryKey, String> inputs) {
        if (inputs.isEmpty()) {
            System.out.println("Currently there are no filter conditions set.");
            return;
        }
        System.out.print("Filter conditions:\n" + inputs + "\n");
    }

    public static void showInvalidParameter(String key, InvalidParameterException e) {
        System.out.println(e.getMessage());
        System.out.println(TermDefinition.getExample(key));
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

    public static void showEmptyParameter(EmptyParameterException e) {
        System.out.println("\"FIND\"" + e.getMessage());
        System.out.println("Please specify a filter to use before executing this command.");
        System.out.println("Filters available: " + Arrays.asList(QueryKey.values()));
        System.out.println("An example will be \"filter location clementi\"");
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
}
