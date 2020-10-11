package seedu.duke.utility;

/*TODO include more parser classes (storage.parser, command.parser etc in the future)
 *  Use save/load function to load help commands to user instead
 * */

import seedu.duke.commands.UpdateShowEpisodeProgressCommand;
import seedu.duke.commands.UpdateShowSeasonCommand;

/**
 * Represents a parser to process the commands inputted by the user.
 */
public class InputParser {
    private static final String PATH_MANUAL = "manual.txt";
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

    private static java.util.ArrayList<String> tokenizeStringArray(String input) {
        java.util.ArrayList<String> inputArray = new java.util.ArrayList<>();
        for (String token : input.split(" ")) {
            inputArray.add(token);
        }
        int size = 0;
        try {
            size = inputArray.size();
        } catch (NullPointerException e) {
            Ui.printBadInputException();
        }
        if (size > 0) {
            return inputArray;
        } else {
            return null;
        }
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

        case "episode":
            java.util.ArrayList<String> updateInputs = tokenizeStringArray(input);
            UpdateShowEpisodeProgressCommand updateShowProgress =
                    new UpdateShowEpisodeProgressCommand(command, updateInputs);
            updateShowProgress.processCommand();
            return;
        case "season":
            java.util.ArrayList<String> seasonInputs = tokenizeStringArray(input);
            UpdateShowSeasonCommand updateShowSeason = new UpdateShowSeasonCommand(command, seasonInputs);
            updateShowSeason.processCommand();
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

            return parseChangeRatingCommand(input);

        case "save":
            return parseSaveCommand();*/

        case "":
            Ui.printNoInputException();
            return;

        default:
            Ui.printBadInputException();
        }
    }

    //todo: how to differentiate between show and movie HOW??HOW??HOW??HOW??HOW??

    /*
    private static void parseAddCommand(String input) {
        //catch for 1)too many/not enough inputs , 2)invalid input, 3)show alr added
        //maybe last arg is optional? idk cos user might not wanna put rating yet
        String[] tokenizedInput = input.split(" ");
        //add show detail into appropriate class
        Ui.printShowAdded(tokenizedInput[0]);
    }

    private static seedu.duke.commands.Command parseEditCommand(String input) {
        //this one might need check agn, the current i/o is a yikes imo
        seedu.duke.utility.Ui.queryEditShow(input);
        java.util.Scanner scan = null;
        boolean isEditFinished = false;
        while (!isEditFinished)){
            System.out.println("Name: (Press enter to skip)");
            //edit name
            System.out.println("Episodes: (Press enter to skip or done to exit)");
            //edit episode
            System.out.println("Seasons: (Press enter to skip or done to exit)");
            //edit seasons
        }

    }

    private static Command parseAddRatingCommand(String input) {
        // catch exceptions for 1)not enough inputs, 2)invalid args,
        // 3)invalid rating , if rating alr exists change it
        // split input using tokenizer
        String[] tokenizedInput = input.split(" ");
        int showRating = Integer.parseInt(tokenizedInput[1]);
        Ui.printShowRating(tokenizedInput[0] , tokenizedInput[1]);
    }
    private static Command parseListCommand() {
        // idk how to do this btw
        Ui.printSavedList();
    }
    private static void parseDeleteCommand(String input) {
        //catch for 1)show not found , 2) invalid no. of args
        Ui.printDeleteShow(input);
    }

    private static void parseDeleteRatingCommand(String input) {
        // catch exceptions for 1)not enough inputs, 2)invalid args
        // check if use same class as ChangeRatingCommand
        // make rating 0 or delete??
        ChangeRatingCommand changeShowRating = new ChangeRatingCommand(input);
        changeShowRating.changeRating(0);
        Ui.printDeleteRating(input);
    }

    private static void parseChangeRatingCommand(String input) {
        // catch exceptions for 1)not enough inputs, 2)invalid args, 3)invalid rating
        // split input using tokenizer
        String[] tokenizedInput = input.split(" ");
        int showRating = Integer.parseInt(tokenizedInput[1]);
        ChangeRatingCommand changeShowRating = new ChangeRatingCommand(tokenizedInput[0]);
        changeShowRating.changeRating(showRating);
        Ui.printChangeRating(tokenizedInput[0] , tokenizedInput[1]);
    }

    private static void parseSaveCommand() {
        // run save method
        Ui.printSavedList();
    }*/

}

