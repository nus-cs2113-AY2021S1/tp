package ui;

import access.Access;

import commands.AddCommand;
import commands.BackCommand;
import commands.EditCommand;
import commands.ExcludeCommand;
import commands.ExitCommand;
import commands.GoCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.IncludeCommand;
import commands.ListCommand;
import commands.ListDueCommand;
import commands.PreviewCommand;
import commands.RemoveCommand;
import commands.RescheduleCommand;
import commands.ReviseCommand;
import commands.ShowRateCommand;
import manager.card.Card;
import manager.chapter.DueChapter;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static commands.ReviseCommand.MESSAGE_SHOW_ANSWER_PROMPT;
import static common.Messages.LINE;

/**
 * UI of the application.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static boolean chooseToRateNewDeck() {
        System.out.println("Would you like to rate this new Chapter? (Y/N)");
        Ui ratingUi = new Ui();
        String userChoice = ratingUi.readCommand();
        while (!userChoice.equalsIgnoreCase("Y") && !userChoice.equalsIgnoreCase("N")) {
            ratingUi.showToUser("Sorry, that is not a valid input. Please enter \"Y\" or \"N\"");
            userChoice = ratingUi.readCommand();
        }
        return userChoice.equalsIgnoreCase("Y");
    }

    public static boolean validDeckRating(String rating) {
        switch (rating.toUpperCase()) {
        case "E":
        case "M":
        case "H":
            return true;
        default:
            return false;
        }
    }

    public static String getChoiceOfNewDeckRating() {
        System.out.println("Please rate this new Chapter!");
        System.out.println("You have the options of: Easy(E), Medium(M) or Hard(H)");
        System.out.println("Would your choice be E, M or H?");
        Ui ratingUi = new Ui();
        String userChoice = ratingUi.readCommand();
        while (!validDeckRating(userChoice)) {
            userChoice = ratingUi.readCommand();
        }
        return userChoice.toUpperCase();
    }

    /** Prompts for the command and reads the text entered by the user.
     *
     * @return full command entered by the user
     */
    public String readCommand() {
        out.print("Enter command here: ");
        String userCommand = in.nextLine();
        while (userCommand.trim().isEmpty()) {
            out.print("Enter command here: ");
            userCommand = in.nextLine();
        }
        return userCommand;
    }

    /**
     * Prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        out.println("Welcome to Kaji!");
    }

    /**
     * Prints the access level that the user is currently at.
     *
     * @param access access level of the user
     */
    public void showLevel(Access access) {
        out.println(access.getLevel());
    }

    /**
     * Prints the line divider between each command.
     */
    public void printLine() {
        out.println(LINE);
    }

    /**
     * Shows message to the user.
     *
     * @param message message to be shown to the user
     */
    public void showToUser(String message) {
        out.println(message);
    }

    public void showCardRevision(Card c, Scanner scanner) {
        out.println(c.getRevisionQuestion() + MESSAGE_SHOW_ANSWER_PROMPT);
        getAnswerInput(c, scanner);
    }

    public void getAnswerInput(Card c, Scanner scanner) {
        String input = scanner.nextLine();
        while (!input.trim().equalsIgnoreCase("s")) {
            out.println("You have entered an invalid input, please try again.");
            input = scanner.nextLine();
        }
        out.println(c.getRevisionAnswer());
    }


    public String getInput(String prompt, Scanner scanner) {
        out.println(prompt);
        return scanner.nextLine();
    }

    /**
     * Prints the exit message upon termination of the application.
     */
    public void showExit() {
        out.println("Exiting the program...");
    }

    /**
     * Prints the list of commands available.
     */
    public void showHelpList() {
        out.println("Here is a list of commands available:" + "\n");
        out.println("1.  " + AddCommand.MESSAGE_USAGE);
        out.println("2.  " + BackCommand.MESSAGE_USAGE);
        out.println("3.  " + ListDueCommand.MESSAGE_USAGE);
        out.println("4.  " + EditCommand.MESSAGE_USAGE);
        out.println("5.  " + ExcludeCommand.MESSAGE_USAGE);
        out.println("6.  " + ExitCommand.MESSAGE_USAGE);
        out.println("7.  " + GoCommand.MESSAGE_USAGE);
        out.println("8.  " + HelpCommand.MESSAGE_USAGE);
        out.println("9.  " + HistoryCommand.MESSAGE_USAGE);
        out.println("10. " + IncludeCommand.MESSAGE_USAGE);
        out.println("11. " + ListCommand.MESSAGE_USAGE);
        out.println("12. " + PreviewCommand.MESSAGE_USAGE);
        out.println("13. " + RemoveCommand.MESSAGE_USAGE);
        out.println("14. " + RescheduleCommand.MESSAGE_USAGE);
        out.println("15. " + ReviseCommand.MESSAGE_USAGE);
        out.println("16. " + ShowRateCommand.MESSAGE_USAGE);
    }

    /**
     * Prints the error message from exceptions.
     *
     * @param error error message thrown by exceptions
     */
    public void showError(String error) {
        out.println(error);
    }

    public void printDueChapters(ArrayList<DueChapter> dueChapters) {
        for (DueChapter dueChapter : dueChapters) {
            showToUser("\t-" + dueChapter.toString());
        }
    }

    public void printDueByTodayMessage(int listSize, String commandType) {
        if (listSize == 0) {
            showToUser("You have no tasks due today!");
            if (commandType.equals("due")) {
                showToUser("Please use the \"preview\" command to view upcoming tasks or check back again tomorrow!");
            }
            return;
        }
        System.out.print("The chapter");
        if (listSize > 1) {
            System.out.print("s");
        }
        System.out.print(" you have due by today ");
        if (listSize > 1) {
            showToUser("are:");
        } else {
            showToUser("is:");
        }
    }

    public void printDueByIncrementMessage(int listSize, String incrementedDueBy) {
        if (listSize == 0) {
            showToUser("You have no tasks due on " + incrementedDueBy + "!");
            return;
        }
        System.out.print("The chapter");
        if (listSize > 1) {
            System.out.print("s");
        }
        System.out.print(" you have due by " + incrementedDueBy + " ");
        if (listSize > 1) {
            showToUser("are:");
        } else {
            showToUser("is:");
        }

    }

    public String getExcludedModuleName(String type) {
        if (type.equals(ExcludeCommand.EXCLUDE_COMMAND_OPTION_MODULE)) {
            showToUser("Which module will you like to be excluded from your schedule?");
        } else {
            showToUser("Which module does the chapter you would like to be excluded belong to?");
        }
        return readCommand().toLowerCase();
    }

    public String getExcludedChapterName(String moduleName) {
        showToUser("Which chapter in the Module: " + moduleName + " would you like to exclude?");
        return readCommand().toLowerCase();
    }

    public String getIncludedModuleName(String type) {
        if (type.equals(IncludeCommand.INCLUDE_COMMAND_OPTION_MODULE)) {
            showToUser("Which module will you like to include back into your schedule?");
        } else {
            showToUser("Which module does the chapter you would like to be included belong to?");
        }
        return readCommand().toLowerCase();
    }

    public String getIncludedChapterName(String moduleName) {
        showToUser("Which chapter in the Module: " + moduleName + " would you like to include?");
        return readCommand().toLowerCase();
    }
}
