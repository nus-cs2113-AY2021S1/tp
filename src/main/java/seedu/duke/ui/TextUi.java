package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static seedu.duke.common.Messages.DIVIDER;

/**
 * Text UI of the application.
 */

public class TextUi {

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
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
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
        while (shouldIgnore(fullInputLine)) {
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
     * Shows message(s) to the user.
     */
    public static void showToUser(String message) {
        out.println(message);
    }

    /**
     * Print a divider.
     */
    private static void printDivider() {
        showToUser(DIVIDER);
    }
}
