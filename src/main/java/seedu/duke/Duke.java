package seedu.duke;

import java.util.Scanner;
import seedu.duke.constants.Logos;
import seedu.duke.ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println("Take a quiz with\n" + Logos.DOTTED_CLICKER_LOGO);
        System.out.println("What is your name?");

        Scanner SCANNER = new Scanner(System.in);
        System.out.println("Hello " + SCANNER.nextLine());

    }
}
