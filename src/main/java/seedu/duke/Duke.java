package seedu.duke;

import java.util.Scanner;
import seedu.duke.constants.Logos;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println("Take a quiz with\n" + Logos.LOGO_1);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        System.out.println(Logos.LOGO_1);
    }
}
