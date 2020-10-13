package seedu.duke.ui;

import seedu.duke.human.User;
import java.util.Scanner;

public class Ui {
    private static final Scanner CONSOLE = new Scanner(System.in);
    private static final String LOGO =
            "                 _  _____ _\n"
            + "      /\\         (_)/ ____| |\n"
            + "     /  \\   _ __  _| |    | |__   __ _ _ __\n"
            + "    / /\\ \\ | '_ \\| | |    | '_ \\ / _` | '_ \\\n"
            + "   / ____ \\| | | | | |____| | | | (_| | | | |\n"
            + "  /_/    \\_\\_| |_|_|\\_____|_| |_|\\__,_|_| |_|\n";
    private static final String HORIZONTAL_LINE =
            "-------------------------------------------------------------";

    public void printMessage(String message) {
        if (!message.isBlank()) {
            System.out.println(" " + message);
        }
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(" ☹ OOPS!!! " + errorMessage);
    }

    public void printWelcomeMessage() {
        printMessage("Welcome to AniChan!");
        printMessage(LOGO);
    }

    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public String readInput() {
        System.out.print(" #> ");
        return CONSOLE.nextLine();
    }

    public String readUserInput(String userName, String watchlistName) {
        System.out.print(System.lineSeparator() + " " + userName + " (" + watchlistName + ") #> ");
        String userInput = CONSOLE.nextLine();
        return userInput;
    }

    // TEMPORARY, REMOVED WHEN DONE REFACTORING!
    /**
     * Greets existing user upon opening up application.
     */
    public void greetExisting(User user) {
        String userName = user.getName();
        System.out.println("Welcome Back, " + userName);
        System.out.println();
    }

    /**
     * Prints out available commands.
     */
    public void showMainMenu() {
        System.out.println("1. Add a profile (addprofile)");
        System.out.println("2. Edit a profile (editprofile)");
        System.out.println("3. Browse through all anime (browse)");
        System.out.println("4. Create a watchlist (watchlist)");
        System.out.println("5. Add to watchlist (add)");
        System.out.println("6. Bookmark an anime (bookmark)");
        System.out.println("7. Help (help)");
        System.out.println("8. Exit (exit)");
        System.out.println();
    }

    /**
     * Prints out bye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Sayonara!");
    }
}