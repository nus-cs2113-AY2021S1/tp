package seedu.duke;

import seedu.duke.common.Commands;
import seedu.duke.common.Messages;

import java.util.Scanner;

/**
 * Prints messages.
 */
public class UI {
    public static String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printGreetingMessage() {
        System.out.println(Messages.MESSAGE_GREET);
    }

    public static void printExitMessage() {
        System.out.println(Messages.MESSAGE_BYE);
    }

    public static void printHelpMessage() {
        System.out.println("These are commands Fitr understands: \n"
                + Commands.COMMAND_ADD + "\t Adds food/exercise to Fitr program\n"
                + Commands.COMMAND_SHOW + " <date>\t Shows entries for specified date\n"
                + Commands.COMMAND_DELETE + " <entry index>\t Deletes selected entry\n"
                + Commands.COMMAND_BMI + " /cm <height> /kg <weight>\t Calculates BMI and outputs health risk\n"
                + Commands.COMMAND_EXIT + "\t Exits the program");
    }

    public static void printFoodOrExercise() {
        System.out.println("Would you like to add a food (F) or exercise (E) entry?");
    }

    public static void printDeleteOrBack() {
        System.out.println("Would you like to delete an entry? (Y/N)");
    }

    public static void echoEntry(String entryType, String entry) {
        System.out.println("Okay! Fitr recorded:" + System.lineSeparator()
                + "\t [" + entryType + "] " + entry);
    }

    public static void printDelete(String entry) {
        System.out.println("Done! " + entry + "is removed");
    }

    public static void printInvalidCommandError() {
        System.out.println("Sorry this is an invalid Command!");
    }

    public static void printExerciseListNotFoundError() {
        System.out.println("Exercise List cannot be found");
    }

    public static void printFoodListNotFoundError() {
        System.out.println("Food List cannot be found");
    }

    public static void showError(String message) {
        System.out.println("Error! Error!");
    }
}
