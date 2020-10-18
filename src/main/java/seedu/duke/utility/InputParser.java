package seedu.duke.utility;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ChangeRatingCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DeleteRatingCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.RatingCommand;
import seedu.duke.commands.UpdateShowEpisodeProgressCommand;
import seedu.duke.commands.UpdateShowSeasonCommand;
import seedu.duke.commands.WatchCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static seedu.duke.utility.StringOperations.getFirstWord;
import static seedu.duke.utility.StringOperations.removeFirstWord;
import static seedu.duke.utility.StringOperations.tokenizeStringArray;


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

    /**
     * Parses the input given by user and calls specific Commands
     * while checking the validity of the input.
     *
     * @param input Command entered by user.
     * @return Command based on the user input.
     */
    public String parseInput(String input) {

        String[] singleWordInputs = new String[]{"bye", "list", "help"};
        String command = getFirstWord(input).toLowerCase();

        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            if (!Arrays.asList(singleWordInputs).contains(splitInput[0])) {
                Ui.printInvalidFormatException();
                return command;
            }

        }
        switch (command) {

        case "bye":
            Ui.printByeMessage();
            this.isBye = true;
            return command;

        case "help":
            Ui.printHelp();
            return command;

        case "episode":
            parseEpisodeUpdateCommand(input, command);
            return command;

        case "season":
            parseSeasonUpdateCommand(input, command);
            return command;

        case "rating":
            parseAddRatingCommand(input);
            return command;

        case "deleterating":
            parseDeleteRatingCommand(input);
            return command;

        case "list":
            Ui.printShowList();
            return command;

        case "changerating":
            parseChangeRatingCommand(input);
            return command;

        case "add":
            parseAddCommand(input);
            return command;

        case "delete":
            parseDeleteCommand(input);
            return command;

        case "edit":
            parseEditCommand(input);
            return command;

        case "watch":
            parseWatchCommand(input, command);
            return command;

        case "updatetimelimit":
            parseUpdateTimeLimitCommand(input);
            return command;

        case "":
            Ui.printNoInputException();
            return command;

        default:
            Ui.printBadInputException();
            return command;
        }
    }

    private static void parseEditCommand(String input) {
        ArrayList<String> tokenizedString = tokenizeStringArray(input);
        try {
            EditCommand edit = new EditCommand(tokenizedString.get(1));
            edit.processCommand();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify show name");
            return;
        } catch (NullPointerException e) {
            Ui.printInvalidEpisodesInputException();
            return;
        }
    }

    private static void parseUpdateTimeLimitCommand(String input) {
        input = removeFirstWord(input);
        int newWatchLimit = Integer.parseInt(input);
        /*try {
            WatchTime changeWatchLimit = new WatchTime(//insert args here);
            changeWatchLimit.watchDurationUpdate(newWatchLimit);
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidFormatException();
            return;
        } catch (NullPointerException e) {
            Ui.printBadInputException();
            return;
        }*/
    }

    private static void parseWatchCommand(String input, String command) {
        ArrayList<String> tokenizedString = tokenizeStringArray(input);
        try {
            WatchCommand showWatched = new WatchCommand(command, tokenizedString);
            showWatched.processCommand();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify show name");
            return;
        } catch (NullPointerException e) {
            Ui.printNotFoundException();
            return;
        }
    }

    private static void parseEpisodeUpdateCommand(String input, String command) {
        ArrayList<String> updateInputs = tokenizeStringArray(input);
        UpdateShowEpisodeProgressCommand updateShowProgress;
        try {
            updateShowProgress = new UpdateShowEpisodeProgressCommand(command, updateInputs);
        } catch (NullPointerException e) {
            Ui.printBadInputException();
            return;
        }
        updateShowProgress.processCommand();

    }

    private static void parseSeasonUpdateCommand(String input, String command) {
        ArrayList<String> seasonInputs = tokenizeStringArray(input);
        UpdateShowSeasonCommand updateShowSeason;
        try {
            updateShowSeason = new UpdateShowSeasonCommand(command, seasonInputs);
        } catch (NullPointerException e) {
            Ui.printBadInputException();
            return;
        }
        updateShowSeason.processCommand();
    }

    private static void parseAddRatingCommand(String input) {
        input = removeFirstWord(input);
        try {
            String[] tokenizedInput = input.split(" ");
            int showRating = Integer.parseInt(tokenizedInput[1]);
            RatingCommand newShowRating = new RatingCommand(tokenizedInput[0]);
            newShowRating.rateShow(tokenizedInput[0], showRating);
            Ui.printShowRating(tokenizedInput[0], tokenizedInput[1]);
        } catch (NullPointerException e) {
            Ui.printBadInputException();
            return;
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidRatingInput();
        }
    }

    private static void parseDeleteRatingCommand(String input) {
        input = removeFirstWord(input);
        DeleteRatingCommand deleteShowRating = new DeleteRatingCommand(input);
        try {
            deleteShowRating.deleteRating(input);
            Ui.printDeleteRating(input);
        } catch (Exception e) {
            Ui.printNotFoundException();
        }
    }

    private static void parseChangeRatingCommand(String input) {
        input = removeFirstWord(input);
        try {
            String[] tokenizedInput = input.split(" ");
            int showRating = Integer.parseInt(tokenizedInput[1]);
            ChangeRatingCommand changeShowRating = new ChangeRatingCommand(tokenizedInput[0]);
            changeShowRating.changeRating(tokenizedInput[0], showRating);
            Ui.printChangeRating(tokenizedInput[0], tokenizedInput[1]);
        } catch (NullPointerException e) {
            Ui.printBadInputException();
            return;
        }
    }

    private static void parseAddCommand(String input) {
        String[] tokenizedInput = input.split(" ");
        try {
            new AddCommand(tokenizedInput);
        } catch (NullPointerException e) {
            Ui.printInvalidEpisodesInputException();
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidFormatException();
            return;
        }
        Ui.printShowAdded(tokenizedInput[1]);
    }

    private static void parseDeleteCommand(String input) {
        input = removeFirstWord(input);
        DeleteCommand deletingShow = new DeleteCommand(input);
        try {
            deletingShow.delete(input);
            Ui.printDeleteShow(input);
        } catch (Exception e) {
            Ui.printNotFoundException();
        }
    }
}

