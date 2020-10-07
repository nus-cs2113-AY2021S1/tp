package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.constants.Logos;
import static seedu.duke.database.UserSettingsLoader.loadUserSettings;
import static seedu.duke.ui.UI.printDivider;
import static seedu.duke.ui.UI.printFarewellMessage;
import static seedu.duke.ui.UI.printHelloMessage;
import seedu.duke.wordlist.WordList;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Settings set to defaults.
     **/
    private static final int NUMBER_OF_SETTINGS = 1; // currently only username
    public static String username = "User";
    public static ArrayList<String> savedSettings = new ArrayList<>(NUMBER_OF_SETTINGS);


    /**
     * Main entry-point for the CLIcker application.
     */
    public static void main(String[] args) {
        setUserSettingsArrayList(savedSettings, username);
        loadUserSettings(savedSettings);
        printDivider();
        username = savedSettings.get(0);

        System.out.println("Take a quiz with\n" + Logos.DOTTED_CLICKER_LOGO);
        printHelloMessage(username);
        boolean isExit = false;
        while (!isExit) {
            switch (SCANNER.next()) {
            case "noun": {
                String input = SCANNER.nextLine();
                WordList.addNoun(input);
                break;
            }
            case "verb": {
                String input = SCANNER.nextLine();
                WordList.addVerb(input);
                break;
            }
            case "adj": {
                String input = SCANNER.nextLine();
                WordList.addAdjective(input);
                break;
            }
            case "list":
                WordList.listWords();
                break;
            default:
                isExit = true;
                break;
            }
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
