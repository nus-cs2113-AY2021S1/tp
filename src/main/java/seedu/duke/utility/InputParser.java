package seedu.duke.utility;

/*TODO include more parser classes (storage.parser, command.parser etc in the future)
*  Use save/load function to load help commands to user instead
* */

import seedu.duke.classes.Show;
import seedu.duke.commands.ChangeRatingCommand;
import seedu.duke.commands.DeleteRatingCommand;
import seedu.duke.commands.RatingCommand;

import java.util.HashMap;
import java.util.Map.Entry;

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
        //input = removeFirstWord(input);
        switch (command.toLowerCase()) {
        case "bye":
            Ui.printByeMessage();
            this.isBye = true;
            return;

        case "help":
            Ui.printHelp();
            return;

        case "rating":
            parseAddRatingCommand(input);
            return;

        case "deleterating":
            parseDeleteRatingCommand(input);
            return;

        case "list":
            parseListCommand(ShowList.getShowList());
            return;

        case "changerating":
            parseChangeRatingCommand(input);
            return;

        /*case "add":

            return parseAddCommand(input);

        case "edit":

            return parseEditCommand();

        case "delete":

            return parseDeleteCommand();*/

        case "":
            Ui.printNoInputException();
            return;

        default:
            Ui.printBadInputException();
        }
    }

    //todo: differentiate between show and movie soontm

    private static void parseAddRatingCommand(String input) {
        String[] tokenizedInput = input.split(" ");
        int showRating = Integer.parseInt(tokenizedInput[1]);
        RatingCommand newShowRating = new RatingCommand(tokenizedInput[0]);
        newShowRating.rate(tokenizedInput[0], showRating);
        Ui.printShowRating(tokenizedInput[0], tokenizedInput[1]);
    }

    private static void parseDeleteRatingCommand(String input) {
        DeleteRatingCommand deleteShowRating = new DeleteRatingCommand(input);
        deleteShowRating.delete(input);
        Ui.printDeleteRating(input);
    }

    private static void parseChangeRatingCommand(String input) {
        // catch exceptions for 1)not enough inputs, 2)invalid args, 3)invalid rating
        // split input using tokenizer
        String[] tokenizedInput = input.split(" ");
        int showRating = Integer.parseInt(tokenizedInput[1]);
        ChangeRatingCommand changeShowRating = new ChangeRatingCommand(tokenizedInput[0]);
        changeShowRating.changeRating(showRating);
        Ui.printChangeRating(tokenizedInput[0], tokenizedInput[1]);
    }

    private static void parseListCommand(HashMap<String, Show> showList) {
        // idk how to do this btw
        int index = 1;
        for (Entry<String,Show> entry : showList.entrySet()) {
            System.out.print(index + ". " + entry.getValue().getName() + "|" + entry.getValue().getNumSeasons()
                    + "|" + entry.getValue().getEpisodesForSeason(index) + entry.getValue().getRating());
            index++;
        }
    }

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

    private static void parseDeleteCommand(String input) {
        //catch for 1)show not found , 2) invalid no. of args
        Ui.printDeleteShow(input);
    }*/

}

