package seedu.duke.utility;

import seedu.duke.classes.Show;
import seedu.duke.classes.WatchTime;

import java.util.Date;
import java.util.Scanner;

import static java.util.Calendar.getInstance;


//@@author BenardoTang

/**
 * Represents a Ui class that is responsible for Input/Output operations.
 */
public class Ui {

    public static final String SAVE_DIRECTORY = "data/showList.txt";

    private Scanner scan;

    public Ui() {
        scan = new Scanner(System.in);
    }

    public static void printLogo() {
        String logo = " __          __  _______ _____ _    _ _   _ ________   _________ \n"
                + " \\ \\        / /\\|__   __/ ____| |  | | \\ | |  ____\\ \\ / /__   __|\n"
                + "  \\ \\  /\\  / /  \\  | | | |    | |__| |  \\| | |__   \\ V /   | |   \n"
                + "   \\ \\/  \\/ / /\\ \\ | | | |    |  __  | . ` |  __|   > <    | |   \n"
                + "    \\  /\\  / ____ \\| | | |____| |  | | |\\  | |____ / . \\   | |   \n"
                + "     \\/  \\/_/    \\_\\_|  \\_____|_|  |_|_| \\_|______/_/ \\_\\  |_|   \n";

        System.out.println(logo);
    }

    public void hello() {
        printLine();
        printLogo();
        printLine();
        System.out.println("Welcome to WatchNext\n");
        printDailyWatchTracking();
        System.out.println("Type " + ("help") + " to get started!\n");
    }

    public static void printLine() {
        System.out.println("________________________________________________________________________________");
    }

    public static void printByeMessage() {
        printSavedList();
        System.out.println(" Bye. Thank you for using WatchNext <3");
        printLine();
    }

    public static void printHelp() {
        printLine();
        String helpIcon =
                " __    __   _______  __      .______   \n"
                    + "|  |  |  | |   ____||  |     |   _  \\  \n"
                    + "|  |__|  | |  |__   |  |     |  |_)  | \n"
                    + "|   __   | |   __|  |  |     |   ___/  \n"
                    + "|  |  |  | |  |____ |  `----.|  |      \n"
                    + "|__|  |__| |_______||_______|| _|      \n";

        System.out.println(helpIcon);
        System.out.println("The following options are available:");
        System.out.println(("help") + " - Views help\n"
                + " \n"
                + ("add") + " - Adds a show\n"
                + " \n"
                + ("edit") + " - Edits your show details\n"
                + " \n"
                + ("rating") + " - Set rating of your show\n"
                + "\n"
                + ("list") + " - Displays all your shows in list\n"
                + "\n"
                + ("delete") + " - Deletes your show\n"
                + " \n"
                + ("deleterating") + " - Deletes rating of your show\n"
                + "\n"
                + ("changerating") + " - Changes rating of your show\n"
                + "\n"
                + ("episode") + " - Update your episode progress\n"
                + "\n"
                + ("season") + " - Update your season progress\n"
                + "\n"
                + ("updatewatchlimit") + " - Update your watch time limit\n"
                + "\n"
                + ("watch") + " - Update your watch progress\n"
                + "\n"
                + ("bye") + " - Exits the program\n");
        System.out.println("Refer to our user guide for more help!");
        printLine();
    }

    public String getUserCommand() {
        String userInput = scan.nextLine();

        //Take out all empty/whitespace lines
        while (isInputEmpty(userInput)) {
            userInput = scan.nextLine();
        }

        return userInput;
    }

    private boolean isInputEmpty(String rawInput) {
        return rawInput.trim().isEmpty();
    }

    public static void printLineIcon() {
        System.out.println("Enter a command: ");
    }

    public static void printShowList() {
        printLine();
        System.out.println("Your watchlist:");
        for (Show show : ShowList.showList.values()) {
            System.out.println(show.toString());
        }
    }

    public static void printDailyWatchTracking() {
        //Print when user starts program
        Date date = getInstance().getTime();
        System.out.println("It is " + date + " today.");
        System.out.println("Time spent on shows today: ");
        System.out.println("Watch time remaining ");
    }

    public static void printDailyWatchTimeRemaining() {
        //System.out.println("Showtime left : " + watchTime.userReportString() /*the object*/);
    }

    public static void printShowRating(String showName, String rating) {
        printLine();
        System.out.println("The rating for " + (showName) + " has been updated to " + (rating));
    }

    public static void printChangeEpisode(String showName) {
        printLine();
        System.out.println("Updated current episode : " + ShowList.getShow(showName).toString());

    }

    public static void printEditPrompt() {
        System.out.println("Input the detail of the show you want to change {name,season,episode,"
                + "duration of episode} ");
        System.out.println("To finish editing, type 'done'.");
    }

    public static void printEditShow(String showName) {
        printLine();
        System.out.println("Updated show details.");

    }

    public static void printChangeSeason(String showName) {
        printLine();
        System.out.println("Updated current season : " + ShowList.getShow(showName).toString());

    }

    public static void printChangeRating(String showName, String rating) {
        printLine();
        System.out.println("The rating for " + (showName) + " has been updated to " + (rating));
    }

    public static void printDeleteRating(String showName) {
        printLine();
        System.out.println("The rating for " + (showName) + " has been deleted.");
    }

    public static void printDeleteShow(String showName) {
        printLine();
        System.out.println((showName) + " has been deleted.");
    }

    public static void printShowAdded(String showName) {
        printLine();
        System.out.println((showName) + " was added to your watchlist.");
    }

    public static void printSavedList() {
        printLine();
        System.out.println("Your watchlist has been saved.");
    }

    public static void printFinishedAllSeasons(String showName) {
        printLine();
        System.out.println("You have finished all seasons of " + (showName) + " !");
        System.out.println("If there is a new season, please add it using the 'edit' command and input the 'watch' "
                + "command again.");
    }

    public static void printIoException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_IO_EXCEPTION);
    }

    public static void printSpecifyShowName() {
        System.out.println("Please specify show name");
    }

    public static void printInvalidEpisodesInputException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_INVALID_EPISODES_INPUT_EXCEPTION);
    }

    public static void printNoDescriptionException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_NO_DESCRIPTION);
    }

    public static void printNoTimeException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_NO_TIME_DATA);
    }

    public static void printNoInputException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_UNIDENTIFIED_INPUT);
    }

    public static void printInvalidDateException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_INVALID_SEARCH_DATE);
    }

    public static void printInvalidFormatException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_INVALID_FORMAT);
    }

    public static void printNotFoundException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_NOT_FOUND_EXCEPTION);
    }

    public static void printBadInputException() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_INVALID_INPUT);
    }

    public static void showCreateFileError() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_CREATE_FILE_ERROR);
    }

    public static void printInvalidRatingInput() {
        System.out.println(ErrorHandling.ExceptionResponse.EXCEPTION_INVALID_RATING_INPUT);
    }

    public static void printDailyWatchTimeLeft() {
        System.out.println("Showtime left : " + WatchTime.userReportString());
        //TODO: format the ui to print the time left to the user upon watching a new episode

    }
}

