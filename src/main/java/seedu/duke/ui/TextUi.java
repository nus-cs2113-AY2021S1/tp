package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */

public class TextUi {

    private static final String DIVIDER = "===================================================";
    private static final String MESSAGE_WELCOME = "Welcome to your SmartHomeBot V1.0!";
    private static final String MESSAGE_GOODBYE = "Good bye!";
    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    private static Scanner in;
    private static PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(System.in);
        this.out = out;
    }


    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        printDivider();
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        while(in.hasNext()) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine.trim();
    }
    public void showWelcomeMessage() {
        printDivider();
        showToUser(MESSAGE_WELCOME);
    }

    /**
     * Generates and prints the Goodbye message upon the end of the application.
     */
    public void showGoodByeMessage() {
        showToUser(MESSAGE_GOODBYE);
    }

    /**
     * Shows message(s) to the user
     */
    public static void showToUser(String message) {
        out.println(message);
    }

    /**
     * Print a divider
     */
    private static void printDivider() {
        showToUser(DIVIDER);
    }
}
