package seedu.duke.ui;

import seedu.duke.Duke;
import seedu.duke.human.User;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.AniLogger.getAniLogger;

public class Ui {
    private static final Scanner CONSOLE = new Scanner(System.in);
    private static final Logger LOGGER = getAniLogger(Duke.class.getName());
    private static final String LOGO =
                    "                  _  _____ _\n"
                    + "      /\\         (_)/ ____| |\n"
                    + "     /  \\   _ __  _| |    | |__   __ _ _ __\n"
                    + "    / /\\ \\ | '_ \\| | |    | '_ \\ / _` | '_ \\\n"
                    + "   / ____ \\| | | | | |____| | | | (_| | | | |\n"
                    + "  /_/    \\_\\_| |_|_|\\_____|_| |_|\\__,_|_| |_|\n";
    private static final String HORIZONTAL_LINE =
            "-------------------------------------------------------------";


    public void printMessage(String message) {
        if (!message.isBlank()) {
            System.out.println(message);
        }
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printWelcomeMessage() {
        printMessage("Welcome to AniChan!");
        printMessage(LOGO);
    }

    public void printGoodbyeMessage(String name) {
        printMessage("Sayonara " + name + "!");
        LOGGER.log(Level.INFO, "Printed goodbye message, exiting program!");
    }

    public String readInput() {
        System.out.print("#>");
        return CONSOLE.nextLine();
    }

    public String readUserInput(User user) {
        String workspaceName = user.getActiveWorkspace().getName();
        String watchlistName = user.getActiveWorkspace().getActiveWatchlistName();
        System.out.print(System.lineSeparator() + workspaceName + " (" + watchlistName + ") #> ");
        return CONSOLE.nextLine();
    }

    public String[] createUserDialogue() {
        LOGGER.log(Level.INFO, "No existing user file found, prompting user to create one!");
        String[] userDialogueInput = new String[3];

        printMessage("What's your name?");
        userDialogueInput[0] = readInput();
        printMessage("Hello " + userDialogueInput[0] + "! What might your gender be? (Male/Female/Other)");
        userDialogueInput[1] = readInput();

        return userDialogueInput;
    }
}
