package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "    ____  __      _   ____  _______\n"
                + "   / __ \\/ /___ _/ | / / / / / ___/\n"
                + "  / /_/ / / __ `/  |/ / / / /\\__ \\ \n"
                + " / ____/ / /_/ / /|  / /_/ /___/ / \n"
                + "/_/   /_/\\__,_/_/ |_/\\____//____/  \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
