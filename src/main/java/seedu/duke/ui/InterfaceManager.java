package seedu.duke.ui;

import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.timetable.Timetable;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a InterfaceManager. Manages the input and output of the application.
 */
public class InterfaceManager {
    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();

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

    /**
     * Prints the list of notes in the notebook.
     *
     * @param notebook Containing the notes.
     */
    private void prints(Notebook notebook) {

    }

    /**
     * Prints all the events in the timetable.
     *
     * @param timetable Containing all the events.
     */
    private void prints(Timetable timetable) {

    }
}
