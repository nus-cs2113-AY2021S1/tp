package seedu.duke.ui;

import seedu.duke.bunny.Bunny;
import seedu.duke.constants.ClickerMessages;

import java.util.HashMap;

import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.constants.Logos.CAT_TEXT_DIVIDER;
import static seedu.duke.constants.Logos.FENCE_TEXT_DIVIDER;
import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;

public class UI {
    public static final String NEWLINE = System.lineSeparator();

    /**
     * Line divider set to default before settings are loaded. Default is the first one.
     */
    public static String currentLineDivider = getDivider(1);

    // Main Help function
    public static void printHelpMessage(String input) {
        switch (input) {
        case "noun":
            printHelpNoun();
            break;
        case "verb":
            printHelpVerb();
            break;
        case "adj":
            printHelpAdj();
            break;
        case "settings":
            printHelpSettings();
            break;
        case "list":
            printHelpList();
            break;
        case "history":
            printHelpHistory();
            break;
        case "stats":
            printHelpStats();
            break;
        case "clear":
            printHelpClear();
            break;
        case "exit":
            printHelpExit();
            break;
        default:
            printHelp();
            break;
        }
    }

    public static void printHelp() {
        String[] listCommands = {"noun", "verb", "adj", "settings", "list", "stats", "start", "clear", "exit"};
        System.out.println("Type 'help <function name here>' to view help for each command.");
        System.out.println("Available commands:");
        for (String listCommand : listCommands) {
            System.out.println(listCommand);
        }
    }

    public static void printHelpSettings() {
        System.out.println("Use the following format for the 'settings' command:");
        System.out.println("settings n\\<name> d\\<divider option>");
        System.out.println("Example: settings n\\Alice d\\3");
    }

    public static void printHelpNoun() {
        System.out.println("Type in the noun you want to save followed by its meaning.");
        System.out.println("Use the following format for the 'noun' command:");
        System.out.println("noun <word here> d\\<description>");
        System.out.println("Example: noun cat d\\a feline animal");
    }

    public static void printHelpVerb() {
        System.out.println("Type in the verb you want to save followed by its meaning.");
        System.out.println("Use the following format for the 'verb' command:");
        System.out.println("verb <word here> d\\<description>");
        System.out.println("Example: verb eat d\\to consume food");
    }

    public static void printHelpAdj() {
        System.out.println("Type in the adjective you want to save followed by its meaning.");
        System.out.println("Use the following format for the 'adj' command:");
        System.out.println("adj <word here> d\\<description>");
        System.out.println("Example: adj hungry d\\the feeling due to lack of food");
    }

    public static void printHelpList() {
        System.out.println("List all available words by typing 'list'.");
    }

    public static void printHelpHistory() {
        System.out.println("Show history and results of quizzes taken in this session by typing 'history'.");
    }

    public static void printHelpStats() {
        System.out.println("Show your results and statistics of a specific quiz by typing 'quiz t\\QUIZ_INDEX'.");
        System.out.println("Example: quiz t\\3");
    }

    public static void printHelpClear() {
        System.out.println("Delete all your saved quizzes in CLIcker by typing 'clear'. "
                + "A warning prompt will be generated before deletion.");
    }

    public static void printHelpExit() {
        System.out.println("Exit the program by typing 'exit'.");
    }

    public static String getDivider(int input) {
        String divider = "";
        if (input == 1) {
            divider = PLAIN_TEXT_DIVIDER;
        } else if (input == 2) {
            divider = CAT_TEXT_DIVIDER;
        } else if (input == 3) {
            divider = FENCE_TEXT_DIVIDER;
        }
        return divider;
    }

    /**
     * Prints the personalised greeting message.
     *
     * @param username user input name
     */
    public static void printHelloMessage(String username) {
        printDivider();
        System.out.printf(ClickerMessages.HELLO_GREETING, username);
        printDivider();
    }

    /**
     * Prints the personalised farewell message.
     *
     * @param username user indicated name
     */
    public static void printFarewellMessage(String username) {
        System.out.printf(ClickerMessages.FAREWELL_GREETING, username);
    }

    /**
     * Prints the line divider to the console.
     */
    public static void printDivider() {
        System.out.println(currentLineDivider);
    }

    public static void addBunnyMessage(String bunny) {
        System.out.println(ClickerMessages.ADD_BUNNY_MSG + "\n" + bunny);
    }

    public static void bunnyWrongFormat() {
        System.out.println(ClickerMessages.BUNNY_WRONG_FORMAT);
    }

    public static void bunnyMissingIdea() {
        System.out.println(ClickerMessages.BUNNY_MISSING_IDEA_MSG);
    }

    public static void listBunnyMessage() {
        System.out.println(ClickerMessages.LIST_BUNNY_MSG);
    }

    public static void listCharacterMessage() {
        System.out.println(ClickerMessages.LIST_CHARACTER_MSG);
    }

    public static void addNounMessage(String noun) {
        System.out.println(ClickerMessages.ADD_NOUN_MSG + noun);
    }

    public static void addVerbMessage(String verb) {
        System.out.println(ClickerMessages.ADD_VERB_MSG + verb);
    }

    public static void addAdjectiveMessage(String adj) {
        System.out.println(ClickerMessages.ADD_ADJECTIVE_MSG + adj);
    }

    public static void listWordsMessage() {
        System.out.println(ClickerMessages.LIST_WORDS_MSG);
    }

    public static void commandNotRecognisedMsg() {
        System.out.println(ClickerMessages.COMMAND_UNRECOGNISED_MSG);
    }

    public static void numBunnyLoaded(int numBunnies, int numBunniesLoaded) {
        System.out.println(String.format("%1$s of %2$s bunnies loaded!", numBunniesLoaded, numBunnies));
    }

    public static void createNewBunnyFile() {
        System.out.println("Writing to bunny file");
    }

    public static void failedToSaveBunny() {
        System.out.println("Failed to save bunny list");
    }

    public static void bunnySaved() {
        System.out.println("Bunny list saved!");
    }

    public static void printFilteredBunny(int numBunny, HashMap<Integer, Bunny> filteredBunny) {
        int i;
        int bunnyFiltered = 0;
        for (i = 1; i <= numBunny; i++) {
            if (filteredBunny.containsKey(i)) {
                System.out.println((i) + ".\n" + bunniesList.get(i - 1).getDescription());
                bunnyFiltered++;
            }
        }

        bunnyFilterSummary(bunnyFiltered);
    }

    private static void bunnyFilterSummary(int bunnyFiltered) {
        System.out.println("number bunny filtered: " + bunnyFiltered);
    }

    public static void bunnyMissingFilterOption() {
        System.out.println("filter bunny command missing filter options.");
    }

    public static void bunnyFilterNoneFound() {
        System.out.println("No bunny matching filter options");

    }

    public static void printAskForName(String username) {
        System.out.print(ClickerMessages.ASK_FOR_NAME);
    }
}
