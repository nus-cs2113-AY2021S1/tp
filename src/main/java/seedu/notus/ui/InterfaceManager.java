package seedu.notus.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

//@@author Nazryl
/**
 * Represents a InterfaceManager. Manages the input and output of the application.
 */
public class InterfaceManager {

    private final Scanner in;
    private final PrintStream out;

    public InterfaceManager() {
        this(System.in, System.out);
    }

    public InterfaceManager(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the command based on user input.
     *
     * @return user input command.
     */
    public String getUserCommandInput() {
        return in.nextLine();
    }

    /**
     * Prints a message with a default format.
     *
     * @param message Message to be printed out.
     */
    public void prints(String message) {
        out.println(message);
    }
}
