package seedu.duke;

import seedu.duke.commands.CommandChecker;
import seedu.duke.constants.Logos;
import seedu.duke.storage.FileFunctions;
import seedu.duke.storage.writing.WritingsLoader;
import seedu.duke.common.User;
import seedu.duke.writing.WritingList;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.commands.CommandChecker.UNRECOGNISED;
import static seedu.duke.commands.CommandChecker.extractCommandType;
import static seedu.duke.constants.FilePaths.WRITING_FILE_PATH;
import static seedu.duke.storage.bunny.BunnyLoader.loadBunnyFile;
import static seedu.duke.storage.UserSettingsLoader.loadUserSettings;
import static seedu.duke.storage.word.WordsLoader.loadWordsFile;
import static seedu.duke.storage.writing.WritingsLoader.loadWritings;
import static seedu.duke.storage.writing.WritingsLoader.recordListToFile;
import static seedu.duke.commands.CommandExecutor.executeCommand;
import static seedu.duke.common.Parsers.getUserInput;
import static seedu.duke.ui.UI.printAskForName;
import static seedu.duke.ui.UI.printDivider;
import static seedu.duke.ui.UI.printFarewellMessage;
import static seedu.duke.ui.UI.printHelloMessage;

public class Duke {
    private static final WritingsLoader loader = new WritingsLoader();
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Settings set to defaults.
     **/
    private static final int NUMBER_OF_SETTINGS = 1; // currently only username
    public static String username = "User";
    public static ArrayList<String> savedSettings = new ArrayList<>(NUMBER_OF_SETTINGS);
    public static WritingList writings = new WritingList();
    public static User user;

    /**
     * Main entry-point for the Fluffle application.
     */
    public static void main(String[] args) {
        setUserSettingsArrayList(savedSettings, username);
        loadUserSettings(savedSettings);
        loadBunnyFile(bunniesList);
        loadWritings(writings);
        loadWordsFile();
        printDivider();
        username = savedSettings.get(0);

        System.out.println("Write a story with\n" + Logos.BIG_FLUFFLE_LOGO);

        printHelloMessage(username);
        printAskForName(username);
        username = getUserInput(SCANNER);
        user = new User(username);
        user.greetUser();
        user.printInstruction();
        String userInput;
        CommandChecker commandChecker = UNRECOGNISED;

        while (commandChecker != CommandChecker.EXIT) {
            userInput = getUserInput(SCANNER);
            //echoInput(userInput); //for testing only
            printDivider();
            commandChecker = extractCommandType(userInput);
            executeCommand(commandChecker, userInput);
            printDivider();
        }
        File f = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
        recordListToFile(f, writings);
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
