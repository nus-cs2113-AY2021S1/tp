package seedu.duke;

/**
 * Prints messages
 */
public class UI {
    private static final String MESSAGE_GREET = "Hello! Welcome to Fitr. "
            + System.lineSeparator() + "What can I do for you?";
    private static final String MESSGAGE_BYE = "Bye. Hope to see you again soon!";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_SHOW = "show";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BMI = "bmi";
    private static final String COMMAND_EXIT = "exit";

    public void printGreetingMessage() {
        System.out.println(MESSAGE_GREET);
    }

    public void printExitMessage() {
        System.out.println(MESSGAGE_BYE);
    }

    public void printHelpMessage() {
        System.out.println("These are commands Fitr understands: \n"
                + COMMAND_ADD + "\t Adds food/exercise to Fitr program\n"
                + COMMAND_SHOW + " <date>\t Shows entries for specified date\n"
                + COMMAND_DELETE + " <entry index>\t Deletes selected entry\n"
                + COMMAND_BMI + " /cm <height> /kg <weight>\t Calculates BMI and outputs health risk\n"
                + COMMAND_EXIT + "\t Exits the program");
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
