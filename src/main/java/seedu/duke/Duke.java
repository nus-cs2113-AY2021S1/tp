package seedu.duke;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.commands.CommandChecker;
import seedu.duke.constants.Logos;
import seedu.duke.history.History;

import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.database.BunnyLoader.loadBunnyFile;
import static seedu.duke.database.UserSettingsLoader.loadUserSettings;


import static seedu.duke.commands.CommandChecker.UNRECOGNISED;
import static seedu.duke.commands.CommandChecker.extractCommandType;

import static seedu.duke.functions.CommandExecutor.executeCommand;
import static seedu.duke.parsers.Parsers.getUserInput;
import static seedu.duke.ui.UI.printDivider;
import static seedu.duke.ui.UI.printFarewellMessage;
import static seedu.duke.ui.UI.printHelloMessage;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Settings set to defaults.
     **/
    private static final int NUMBER_OF_SETTINGS = 1; // currently only username
    private static final int NUMBER_OF_HISTORY = 1;
    public static String username = "User";
    public static ArrayList<String> savedSettings = new ArrayList<>(NUMBER_OF_SETTINGS);
    public static ArrayList<History> savedHistory = new ArrayList<>(NUMBER_OF_HISTORY);


    /**
     * Main entry-point for the Fluffle application.
     */
    public static void main(String[] args) {
        setUserSettingsArrayList(savedSettings, username);
        loadUserSettings(savedSettings);
        loadBunnyFile(bunniesList);

        printDivider();
        username = savedSettings.get(0);

        System.out.println("Write a story with\n" + Logos.BIG_FLUFFLE_LOGO);
        printHelloMessage(username);

        String userInput;
        CommandChecker commandChecker = UNRECOGNISED;

        while (commandChecker != CommandChecker.EXIT) {
            userInput = getUserInput(SCANNER);
            printDivider();
            commandChecker = extractCommandType(userInput);
            executeCommand(commandChecker, userInput);
            printDivider();
        }

        printFarewellMessage(username);
    }


    /**
     * Set the values in the array for the saved settings.
     *
     * @param savedSettings Array of saved settings
     * @param username      User input name
     */
    private static void setUserSettingsArrayList(ArrayList<String> savedSettings, String username) {
        savedSettings.add(username);

    }
}
