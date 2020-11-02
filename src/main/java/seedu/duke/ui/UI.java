package seedu.duke.ui;

import seedu.duke.bunny.Bunny;
import seedu.duke.constants.FluffleMessages;
import seedu.duke.exceptions.DividerCommandWrongFormatException;
import seedu.duke.exceptions.DividerIndexOutOfBoundsException;

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
    public static String currentLineDivider = PLAIN_TEXT_DIVIDER;

    public static void changeLineDivider(String userInput)
            throws DividerCommandWrongFormatException, DividerIndexOutOfBoundsException {
        if (userInput.trim().toLowerCase().contains("divider")) {
            int dividerNum = getDividerNumFromInput(userInput);
            currentLineDivider = getDivider(dividerNum);
            UI.changeDividerMessage();
        } else {
            throw new DividerCommandWrongFormatException();
        }
    }

    private static void changeDividerMessage() {
        System.out.println("Changing line divider...");
    }

    public static int getDividerNumFromInput(String userInput) throws DividerIndexOutOfBoundsException {
        int dividerNum;
        try {
            dividerNum = Integer.parseInt(userInput.replaceAll("[\\D]", ""));
            if (dividerNum > 3 || dividerNum < 1) {
                throw new DividerIndexOutOfBoundsException();
            }
        } catch (NumberFormatException exception) {
            throw new DividerIndexOutOfBoundsException();
        }
        return dividerNum;
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
        case "start":
            printHelpStart();
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
        case "name":
            printHelpName();
            break;
        case "list name":
            printHelpListName();
            break;
        case "filter name":
            printHelpFilterName();
            break;
        case "add name":
            printHelpAddName();
            break;
        case "delete name":
            printHelpDeleteName();
            break;
        default:
            printHelp();
            break;
        }
    }

    public static void printHelp() {
        String[] listCommands = {"help", "divider", "bunny", "list bunny", "filter bunny", "save bunny",
            "list", "list filter words", "start", "filter words", "stats", "reset", "name",
            "list name", "filter name", "add name", "delete name", "remind", "clear", "exit"};
        System.out.println("Type 'help <function name here>' to view help for each command.");
        System.out.println("Available commands:");
        for (String listCommand : listCommands) {
            System.out.println(listCommand);
        }
    }

    private static void printHelpDeleteName() {
        System.out.println("Removes a name from the list of names");
        System.out.println("Format: delete name <INDEX>");
    }

    private static void printHelpAddName() {
        System.out.println("Add a name to the list of names");
        System.out.println("Format: add name <NAME>");
    }

    private static void printHelpFilterName() {
        System.out.println("Find all the names from the list of names");
        System.out.println("Format: filter name <NAME>");
    }

    private static void printHelpListName() {
        System.out.println("List all the names from the list of names");
        System.out.println("Format: list name");
    }

    private static void printHelpName() {
        System.out.println("Gets a random name from the list of names");
        System.out.println("Format: name");
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

    public static void printHelpStart() {
        System.out.println("Embark your writings session!");
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

    /**
     * Prints the personalised greeting message.
     *
     * @param username user input name
     */
    public static void printHelloMessage(String username) {
        printDivider();
        System.out.printf(FluffleMessages.HELLO_GREETING, username);
        printDivider();
    }

    /**
     * Prints the personalised farewell message.
     *
     * @param username user indicated name
     */
    public static void printFarewellMessage(String username) {
        System.out.printf(FluffleMessages.FAREWELL_GREETING, username);
    }

    /**
     * Prints the line divider to the console.
     */
    public static void printDivider() {
        System.out.println(currentLineDivider);
    }

    public static void addBunnyMessage(String bunny) {
        System.out.println(FluffleMessages.ADD_BUNNY_MSG + "\n" + bunny);
    }

    public static void bunnyWrongFormat() {
        System.out.println(FluffleMessages.BUNNY_WRONG_FORMAT);
    }

    public static void bunnyMissingIdea() {
        System.out.println(FluffleMessages.BUNNY_MISSING_IDEA_MSG);
    }

    public static void listBunnyMessage() {
        System.out.println(FluffleMessages.LIST_BUNNY_MSG);
    }

    public static void listCharacterMessage() {
        System.out.println(FluffleMessages.LIST_CHARACTER_MSG);
    }

    public static void addNounMessage(String noun) {
        System.out.println(FluffleMessages.ADD_NOUN_MSG + noun);
    }

    public static void addVerbMessage(String verb) {
        System.out.println(FluffleMessages.ADD_VERB_MSG + verb);
    }

    public static void addAdjectiveMessage(String adj) {
        System.out.println(FluffleMessages.ADD_ADJECTIVE_MSG + adj);
    }

    public static void listWordsMessage() {
        System.out.println(FluffleMessages.LIST_WORDS_MSG);
    }

    public static void commandNotRecognisedMsg() {
        System.out.println(FluffleMessages.COMMAND_UNRECOGNISED_MSG);
    }

    public static void numBunnyLoaded(int numBunnies, int numBunniesLoaded) {
        System.out.println(String.format(FluffleMessages.NUM_BUNNY_LOADED_MSG, numBunniesLoaded, numBunnies));
    }

    public static void createNewBunnyFile() {
        System.out.println(FluffleMessages.WRITING_TO_BUNNY_FILE_MSG);
    }

    public static void failedToSaveBunny() {
        System.out.println(FluffleMessages.FAILED_TO_SAVE_BUNNY_LIST_MSG);
    }

    public static void bunnySaved() {
        System.out.println(FluffleMessages.BUNNY_LIST_SAVED_MSG);
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
        System.out.println(FluffleMessages.NUMBER_BUNNY_FILTERED_MSG + bunnyFiltered);

    }

    public static void bunnyMissingFilterOption() {
        System.out.println(FluffleMessages.FILTER_BUNNY_COMMAND_MISSING_FILTER_OPTIONS_MSG);
    }

    public static void bunnyFilterNoneFound() {
        System.out.println(FluffleMessages.NO_BUNNY_MATCHING_FILTER_OPTIONS_MSG);
    }

    public static void bunnyDeleted(int bunnyNum) {
        System.out.print(FluffleMessages.BUNNY_DELETED_MSG);
        System.out.print(bunniesList.get(bunnyNum - 1).getDescription());
    }

    public static void printAskForName(String username) {
        System.out.print(FluffleMessages.ASK_FOR_NAME);
    }

    public static void bunnyIndexOutOfBounds() {
        System.out.println(FluffleMessages.BUNNY_INDEX_NOT_IN_LIST_MSG);
    }

    public static void dividerCommandWrongFormat() {
        System.out.println(FluffleMessages.DIVIDER_COMMAND_OF_WRONG_FORMAT_MSG);
    }

    public static void dividerIndexOutOfBounds() {
        System.out.println(FluffleMessages.DIVIDER_INDEX_INDICATED_OUT_OF_BOUNDS_MSG);
    }

    public static void bunnyRandomlySelected(int bunnySelected) {
        System.out.print(FluffleMessages.BUNNY_RANDOMLY_SELECTED_MSG);
        System.out.print(bunniesList.get(bunnySelected).getDescription());
    }

    public static void bunnyListEmpty() {
        System.out.println(FluffleMessages.LIST_BUNNIES_EMPTY_MSG);
    }

    public static void printBunnyInList(String bunny) {
        System.out.println(bunny);
    }

    public static void printNumBunnies(int numBunny) {
        System.out.println(FluffleMessages.TOTAL_BUNNIES_IN_LIST_MSG + numBunny);
    }

    public static void echoInput(String input) {
        System.out.println(input);
    }
}
