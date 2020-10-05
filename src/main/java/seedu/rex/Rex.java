package seedu.rex;

import java.util.Scanner;

public class Rex {
    /**
     * Main entry-point for the java.rex.Rex application.
     */
    public static void main(String[] args) {
        String logo = "  _    _                 _ _        _                                  _____     __   __\n"
                + " | |  | |               (_| |      | |                                |  __ \\    \\ \\ / /\n"
                + " | |__| | ___  ___ _ __  _| |_ __ _| |___  __ _ _   _ _ __ _   _ ___  | |__) |___ \\ V / \n"
                + " |  __  |/ _ \\/ __| '_ \\| | __/ _` | / __|/ _` | | | | '__| | | / __| |  _  // _ \\ > <  \n"
                + " | |  | | (_) \\__ | |_) | | || (_| | \\__ | (_| | |_| | |  | |_| \\__ \\ | | \\ |  __// . \\ \n"
                + " |_|  |_|\\___/|___| .__/|_|\\__\\__,_|_|___/\\__,_|\\__,_|_|   \\__,_|___/ |_|  \\_\\___/_/ \\_\\\n"
                + "                  | |                                                                   \n"
                + "                  |_|                                                                   ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
