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

    public void printGreetingMessage() {
        System.out.println(Messages.MESSAGE_GREET);
    }

    public void printExitMessage() {
        System.out.println(Messages.MESSAGE_BYE);
    }

    public void printHelpMessage() {
        System.out.println("These are commands Fitr understands: \n"
                + Commands.COMMAND_ADD + "\t Adds food/exercise to Fitr program\n"
                + Commands.COMMAND_SHOW + " <date>\t Shows entries for specified date\n"
                + Commands.COMMAND_DELETE + " <entry index>\t Deletes selected entry\n"
                + Commands.COMMAND_BMI + " /cm <height> /kg <weight>\t Calculates BMI and outputs health risk\n"
                + Commands.COMMAND_EXIT + "\t Exits the program");
    }

    public void printFoodOrExercise() {
        System.out.println("Would you like to add a food (F) or exercise (E) entry?");
    }

    public void printDeleteOrBack() {
        System.out.println("Would you like to delete an entry? (Y/N)");
    }

    public void echoEntry(String entryType, String entry) {
        System.out.println("Okay! Fitr recorded:" + System.lineSeparator()
                + "\t [" + entryType + "] " + entry);
    }

    public void printDelete(String entry) {
        System.out.println("Done! " + entry + "is removed");
    }
}
