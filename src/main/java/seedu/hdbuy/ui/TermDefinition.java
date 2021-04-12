package seedu.hdbuy.ui;

import seedu.hdbuy.common.QueryKey;

import java.util.Arrays;

public class TermDefinition {
    /**
     * This method will return the definition of the command.
     *
     * @param key The string value of the command.
     * @return The definition of the command.
     */
    public static String getDefinition(String key) {
        String definition;
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
            // Fallthrough
        case "filter <attribute> <value>":
            definition = "Add a filter condition. eg: filter location woodlands";
            break;
        case "help":
            definition = "It's to help you understand all of our commands";
            break;
        case "list":
            definition = "Show all currently set filter condition to filter units matching preferences";
            break;
        case "remove":
            // Fallthrough
        case "remove <index>":
            definition = "Remove the unit at the inputted index from the shortlist";
            break;
        case "save":
            // Fallthrough
        case "save <index>":
            definition = "Add the unit at the inputted index to the shortlist";
            break;
        case "shortlist":
            definition = "Show all units in the shortlist";
            break;
        case "sort":
            // Fallthrough
        case "sort <direction>":
            definition = "Sort search results in ascending(asc) or descending(desc) order";
            break;
        default:
            definition = "Invalid definition";
            break;
        }
        return definition;
    }

    /**
     * This method will return an example of the command usage.
     *
     * @param key The string of the command.
     * @return The example of the command usage.
     */
    public static String getExample(String key) {
        String example;
        switch (key) {
        case "clear":
            example = "`clear` command does not need any parameters.";
            break;
        case "exit":
            example = "`exit` command does not need any parameters.";
            break;
        case "find":
            example = "`find` command does not need any parameters.";
            break;
        case "filter":
            example = "Filter types: " + Arrays.asList(QueryKey.values()) + "\nExample: \"filter location clementi\"\n"
                + "Example: \"filter type 4 room\", can be any of X room (X = 1 - 5) or executive\nExample: \"filter "
                + "lease_remaining 90\", can be any whole number from 0 - 99";
            break;
        case "help":
            example = "`help` command does not need any parameters.";
            break;
        case "list":
            example = "`list` command does not need any parameters.";
            break;
        case "remove":
            example = "Example: \"remove X\", X is the index of the unit in the shortlist.";
            break;
        case "save":
            example = "Example: \"save X\", X is the index of the unit from the search result.";
            break;
        case "shortlist":
            example = "`shortlist` command does not need any parameters.";
            break;
        case "sort":
            example = "Example: \"sort asc\" or \"sort desc\"";
            break;
        default:
            example = "Invalid example.";
            break;
        }
        return example;
    }
}
