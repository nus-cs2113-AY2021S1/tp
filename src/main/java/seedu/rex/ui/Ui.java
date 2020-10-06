package seedu.rex.ui;

import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
import seedu.rex.storage.Storage;

import java.util.Scanner;

/**
 * Interacts with user.
 */
public class Ui {

    public static final String LOGO =
            "  _    _                 _ _        _                                  _____     __   __\n"
            + " | |  | |               (_| |      | |                                |  __ \\    \\ \\ / /\n"
            + " | |__| | ___  ___ _ __  _| |_ __ _| |___  __ _ _   _ _ __ _   _ ___  | |__) |___ \\ V / \n"
            + " |  __  |/ _ \\/ __| '_ \\| | __/ _` | / __|/ _` | | | | '__| | | / __| |  _  // _ \\ > <  \n"
            + " | |  | | (_) \\__ | |_) | | || (_| | \\__ | (_| | |_| | |  | |_| \\__ \\ | | \\ |  __// . \\ \n"
            + " |_|  |_|\\___/|___| .__/|_|\\__\\__,_|_|___/\\__,_|\\__,_|_|   \\__,_|___/ |_|  \\_\\___/_/ \\_\\\n"
            + "                  | |                                                                   \n"
            + "                  |_|                                                                   ";
    private static final String DOTTED_LINE = "____________________________________________________________";
    private final Scanner in = new Scanner(System.in);

    /**
     * Prints string with indent.
     *
     * @param string String to print.
     */
    private void printWithIndent(String string) {
        System.out.println("\t " + string);
    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        System.out.println("\t" + DOTTED_LINE);
    }

    /**
     * Prints error message.
     *
     * @param message Error message to print.
     */
    public void showError(String message) {
        printWithIndent(message);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        printWithIndent(Command.MESSAGE);
        showLine();
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        showError(Storage.LOAD_ERROR);
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        printWithIndent(ExitCommand.MESSAGE);
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        System.out.println();
        return in.nextLine();
    }
}
