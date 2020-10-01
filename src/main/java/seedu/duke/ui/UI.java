package seedu.duke.ui;

import static seedu.duke.constants.ClickerMessages.FAREWELL_GREETING;
import static seedu.duke.constants.ClickerMessages.HELLO_GREETING;
import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;

public class UI {
    public static final String NEWLINE = System.lineSeparator();

    /**
     * Line divider set to default before settings are loaded.
     */
    public static String currentLineDivider = PLAIN_TEXT_DIVIDER;

    /**
     * Prints the line divider to the console.
     */
    public static void printDivider() {
        System.out.println(currentLineDivider);
    }

    /**
     * Prints the personalised greeting message.
     *
     * @param username user input name
     */
    public static void printHelloMessage(String username) {
        printDivider();
        System.out.print(String.format(HELLO_GREETING, username));
        printDivider();
    }

    /**
     * Prints the personalised farewell message.
     *
     * @param username user indicated name
     */
    public static void printFarewellMessage(String username) {
        System.out.printf(FAREWELL_GREETING, username);
    }



}
