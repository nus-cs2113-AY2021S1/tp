package seedu.hdbuy.ui;

import seedu.hdbuy.common.QueryKey;

import java.util.Arrays;

public class TermDefinition {
    public static String getDefinition(String key) {
        String definition = "";
        switch (key) {
        case "clear":
            definition = "Remove all currently set filter conditions";
            break;
        case "exit":
            definition = "Exit the application";
            break;
        case "find":
            definition = "Search for units with the current filter conditions";
            break;
        case "filter":
            definition = "Add a filter condition. eg: filter location woodlands";
            break;
        case "help":
            definition = "It's to help you understand all of our commands";
            break;
        case "list":
            definition = "Show all currently set filter condition to filter units matching preferences";
            break;
        case "remove":
            definition = "Remove the unit at the inputted index from the shortlist";
            break;
        case "save":
            definition = "Add the unit at the inputted index to the shortlist";
            break;
        case "shortlist":
            definition = "Show all units in the shortlist";
            break;
        case "sort":
            definition = "Sort search results in ascending(asc) or descending(desc) order";
            break;
        default:
            definition = "Invalid definition";
            break;
        }
        return definition;
    }

    public static String getExample(String key) {
        String example = "";
        switch (key) {
        case "clear":
            example = "CLEAR command does not need any parameters.";
            break;
        case "exit":
            example = "EXIT command does not need any parameters.";
            break;
        case "find":
            example = "FIND command does not need any parameters.";
            break;
        case "filter":
            example = "Example: \"filter location clementi\"\n" + "Example: \"filter type 4 room\", can be any of X "
                    + "room (X = 1 - 5) or executive"
                    + "Example: \"filter lease_remaining 90\", can be any whole number from 0 - 99";
            break;
        case "help":
            example = "HELP command does not need any parameters.";
            break;
        case "list":
            example = "LIST command does not need any parameters.";
            break;
        case "remove":
            example = "Example: \"remove X\", X is the index of the unit in the shortlist.";
            break;
        case "save":
            example = "Example: \"save X\", X is the index of the unit in the shortlist.";
            break;
        case "shortlist":
            example = "SHORTLIST command does not need any parameters.";
            break;
        case "sort":
            example = "Example: \"sort <sorttype>\", <sorttype>: {asc, desc}.";
            break;
        default:
            example = "Invalid example.";
            break;
        }
        return example;
    }
}
