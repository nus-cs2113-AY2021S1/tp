package seedu.duke.ui;

import seedu.duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi that handles user interaction.
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

//    /**
//     * Create a Ui object with default system streams.
//     */
//    public Ui() {
//        this(System.in, System.out);
//    }
//
//    /**
//     * Create a Ui object.
//     *
//     * @param in Input stream to read inputs from the user
//     * @param out Output stream to print to the user
//     */
//    public Ui(InputStream in, PrintStream out) {
//        this.in = new Scanner(in);
//        this.out = out;
//    }

    /**
     * Prints welcome message.
     */
    public static void showWelcomeScreen() {
        showToUser(Messages.MESSAGE_LOGO);
        showToUser(Messages.MESSAGE_WELCOME);
    }

    /**
     * Accepts input from user via input stream.
     *
     * @return Input from user
     */
    public static String getUserCommand() {
        showToUser(Messages.MESSAGE_PROMPT_INPUT);
        String input = in.nextLine().strip();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        return input;
    }

    /**
     * Print messages to user.
     *
     * @param messages Specify messages to print
     */
    public static void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }

}
