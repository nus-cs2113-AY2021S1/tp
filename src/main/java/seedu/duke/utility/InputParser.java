package seedu.duke.utility;

/*TODO include more parser classes (storage.parser, command.parser etc in the future)
*  Use save/load function to load help commands to user instead
* */

/**
 * Represents a parser to process the commands inputted by the user.
 */
public class InputParser {

    private boolean isBye;

    public InputParser() {
        this.isBye = false;
    }

    public boolean isByeTime() {
        return isBye;
    }

    /*
    * In case i need to use it for future commands, might need to see how the others do their commands
    * */
    private static java.util.ArrayList<String> tokenizeToStringArray(String input, String[] argumentDelimiters) {
        int argumentDelimitersIndex = 0;
        String[] inputSplit = input.split(" ");
        java.util.ArrayList<String> argumentArray = new java.util.ArrayList<>();
        StringBuilder argument = new StringBuilder();
        for (int index = 0; index < inputSplit.length; index++) {
            if (argumentDelimitersIndex >= argumentDelimiters.length) {
                argument.append(inputSplit[index]).append(" ");
            } else if (inputSplit[index].equals(argumentDelimiters[argumentDelimitersIndex])) {
                argumentArray.add(argument.toString().trim());
                argumentDelimitersIndex++;
                argument = new StringBuilder();
            } else {
                argument.append(inputSplit[index]).append(" ");
            }
        }
        argumentArray.add(argument.toString().trim());
        return argumentArray;
    }

    private static String getFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index == -1) { // Input only contains a single word
            return input;
        } else {
            return input.substring(0, index).trim(); // Extracts first word.
        }
    }

    private static String removeFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index == -1) { // Input only contains a single word
            return "";
        } else {
            return input.substring(index + 1).trim(); // Extracts after space.
        }
    }

    public void parseInput(String input) {

        String command = getFirstWord(input);
        input = removeFirstWord(input);

        switch (command.toLowerCase()) {
        case "bye":
            Ui.printByeMessage();
            this.isBye = true;
            return;

        case "help":
            Ui.printHelp();
            return;

        /*case "add":

            return parseAddCommand(input);

        case "edit":

            return parseEditCommand();

        case "rating":

            return parseRatingCommand();

        case "list":

            return parseListCommand();

        case "delete":

            return parseDeleteCommand();

        case "deleterating":

            return parseDeleteRatingCommand();

        case "changerating":

            return parseChangeRatingCommand();

        case "save":
            return parseSaveCommand();*/

        default:
            Ui.printNoInputException();
        }
    }
    /*
    private static seedu.duke.commands.Command parseAddCommand(String input) {
    }
    private static seedu.duke.commands.Command parseEditCommand(String input) {
    }
    private static seedu.duke.commands.Command parseRatingCommand(String input) {
    }
    private static seedu.duke.commands.Command parseListCommand() {
    }
    private static seedu.duke.commands.Command parseDeleteCommand(String input) {
    }
    private static seedu.duke.commands.Command parseDeleteRatingCommand(String input) {
    }
    private static seedu.duke.commands.Command parseChangeRatingCommand(String input) {
    }
    private static seedu.duke.commands.Command parseSaveCommand() {
    }*/

}

