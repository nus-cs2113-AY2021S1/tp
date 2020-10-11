package seedu.duke;

import java.util.Scanner;

import static seedu.duke.common.Commands.COMMAND_FOOD;
import static seedu.duke.common.Commands.COMMAND_EXERCISE;
import static seedu.duke.common.Commands.COMMAND_VIEW_FOOD;
import static seedu.duke.common.Commands.COMMAND_VIEW_EXERCISE;
import static seedu.duke.common.Commands.COMMAND_VIEW_SUMMARY;
import static seedu.duke.common.Commands.COMMAND_DELETE;
import static seedu.duke.common.Commands.COMMAND_EXIT;
import static seedu.duke.common.Commands.COMMAND_VIEW;

import static seedu.duke.common.Messages.FORMAT_FOOD;
import static seedu.duke.common.Messages.FORMAT_EXERCISE;
import static seedu.duke.common.Messages.FORMAT_DELETE;

import static seedu.duke.common.Messages.MESSAGE_GREET;
import static seedu.duke.common.Messages.MESSAGE_BYE;
import static seedu.duke.common.Messages.ERROR_INVALID_COMMAND;
import static seedu.duke.common.Messages.ERROR_INVALID_INDEX;
import static seedu.duke.common.Messages.ERROR_INVALID_CALORIES;

/**
 * Prints messages.
 */
public class UI {
    private static final String SPACE = "%-18s%s%n";
    private static final String FORMAT = " ".repeat(18) + "\033[0;32mFormat: \033[0m";

    public static String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printCustomMessage(String message) {
        System.out.println(message);
    }

    public static void printCustomError(String errorMessage) {
        System.out.println("\033[0;31m" + errorMessage + "\033[0m");
    }

    public static void printGreetingMessage() {
        printCustomMessage(MESSAGE_GREET);
    }

    public static void printExitMessage() {
        printCustomMessage(MESSAGE_BYE);
    }


    public static void printHelpMessage() {
        printCustomMessage("These are commands Fitr understands:");

        System.out.printf(SPACE, COMMAND_FOOD, "Adds food entry to Fitr program");
        printCustomMessage(FORMAT + FORMAT_FOOD);

        System.out.printf(SPACE, COMMAND_EXERCISE, "Adds exercise entry to Fitr program");
        printCustomMessage(FORMAT + FORMAT_EXERCISE);

        System.out.printf(SPACE, COMMAND_VIEW_FOOD, "View food entries");
        System.out.printf(SPACE, COMMAND_VIEW_EXERCISE, "View exercise entries");
        System.out.printf(SPACE, COMMAND_VIEW_SUMMARY, "View calorie summary");

        System.out.printf(SPACE, COMMAND_DELETE, "Deletes selected entry");
        printCustomMessage(FORMAT + FORMAT_DELETE);

        System.out.printf(SPACE, COMMAND_EXIT, "Exits the program");
    }

    public static void printFormatError(String command) {
        if(command.equals(COMMAND_VIEW)) {
            printCustomError(ERROR_INVALID_COMMAND);
            System.out.printf(SPACE, COMMAND_VIEW_FOOD, "View food entries");
            System.out.printf(SPACE, COMMAND_VIEW_EXERCISE, "View exercise entries");
            System.out.printf(SPACE, COMMAND_VIEW_SUMMARY, "View calorie summary");
            return;
        }

        printCustomError("Please input in the correct format!");
        switch (command) {
        case COMMAND_FOOD:
            printCustomError(FORMAT_FOOD);
            break;
        case COMMAND_EXERCISE:
            printCustomError(FORMAT_EXERCISE);
            break;
        case COMMAND_DELETE:
            printCustomError(FORMAT_DELETE);
            break;
        }
    }

    public static void printCaloriesError() {
        printCustomError(ERROR_INVALID_CALORIES);
    }

    public static void printIndexError() {
        printCustomError(ERROR_INVALID_INDEX);
    }

    public static void echoEntry(String entry) {
        printCustomMessage("Okay! Fitr recorded:" + System.lineSeparator()
                + "\t" + entry);
    }

    public static void echoDelete(String entry) {
        printCustomMessage("Done! Fitr removed:" + System.lineSeparator()
                + "\t" + entry);
    }

    public static void printInvalidCommandError() {
        printCustomError(ERROR_INVALID_COMMAND);
    }
}
