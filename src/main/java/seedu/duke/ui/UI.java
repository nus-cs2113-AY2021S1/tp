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

    /**
     * Changes the line divider used.
     * @param userInput user input command
     * @throws DividerCommandWrongFormatException the command is wrongly formatted
     * @throws DividerIndexOutOfBoundsException the index for the divider is invalid
     */
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
        System.out.println(FluffleMessages.CHANGING_LINE_DIVIDER_MSG);
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
        case "divider":
            printHelpDivider();
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
        case "bunny":
            printHelpBunny();
            break;
        case "list bunny":
            printHelpListBunny();
            break;
        case "filter bunny":
            printHelpFilterBunny();
            break;
        case "save bunny":
            printHelpSaveBunny();
            break;
        case "delete bunny":
            printHelpDeleteBunny();
            break;
        case "random bunny":
            printHelpRandomBunny();
            break;
        case "filter words":
            printHelpFilterWords();
            break;
        case "list filter words":
            printHelpListFilterWords();
            break;
        case "remind":
            printHelpRemind();
            break;
        default:
            printHelp();
            break;
        }
    }

    public static void printHelp() {
        String[] listCommands = {"help", "divider",
            "bunny", "list bunny", "filter bunny", "save bunny", "delete bunny", "random bunny",
            "list", "list filter words", "start", "filter words", "stats", "reset", "name",
            "list name", "filter name", "add name", "delete name", "remind", "clear", "exit"};
        System.out.println("Type 'help FUNCTION_NAME' to view help for each command.");
        System.out.println("Available commands:");
        for (String listCommand : listCommands) {
            System.out.println("- " + listCommand);
        }
    }

    private static void printHelpDeleteName() {
        System.out.println("Removes a name from the list of names");
        System.out.println("Format: delete name INDEX");
    }

    private static void printHelpAddName() {
        System.out.println("Add a name to the list of names");
        System.out.println("Format: add name NAME");
    }

    private static void printHelpFilterName() {
        System.out.println("Find all the names from the list of names");
        System.out.println("Format: filter name NAME");
    }

    private static void printHelpListName() {
        System.out.println("List all the names from the list of names");
        System.out.println("Format: list name");
    }

    private static void printHelpName() {
        System.out.println("Gets a random name from the list of names");
        System.out.println("Format: name");
    }

    public static void printHelpDivider() {
        System.out.println("Change the line divider used in Fluffle");
        System.out.println("Format: divider DIVIDER_OPTION");
        System.out.println("  Where DIVIDER_OPTION is a number from 1 to 3");
        System.out.println("Example: divider 1");
    }

    private static void printHelpBunny() {
        System.out.println("Add a bunny to the list of bunnies");
        System.out.println("Format: bunny i\\IDEA g\\[GENRE]");
        System.out.println("Examples: bunny i\\some fantasy idea g\\ fantasy");
        System.out.println("          bunny i\\some random idea");
    }

    private static void printHelpListBunny() {
        System.out.println("Prints the list of bunnies");
        System.out.println("Format: list bunny");
    }

    private static void printHelpFilterBunny() {
        System.out.println("Filters specific bunny ideas from the list");
        System.out.println("Format: filter bunny i\\[IDEA] g\\[GENRE]");
        System.out.println("Examples: filter bunny g\\ fantasy");
        System.out.println("          filter bunny i\\ horse");
    }

    private static void printHelpSaveBunny() {
        System.out.println("Save the current list of bunnies");
        System.out.println("Format: save bunny");
    }

    private static void printHelpDeleteBunny() {
        System.out.println("Delete a selected bunny from the list");
        System.out.println("Format: delete bunny BUNNY_INDEX");
        System.out.println("Example: delete bunny 2");
    }

    private static void printHelpRandomBunny() {
        System.out.println("Pick a random bunny from your list");
        System.out.println("Format: random bunny");
    }

    private static void printHelpFilterWords() {
        System.out.println("Get words from your words list.");
        System.out.println("Format: filter words [-continue] [limit\\PRINT_LIMIT] "
                + "by\\TYPE_OF_FILTER -TARGET_STRING...");
        System.out.println("Supported TYPE_OF_FILTER: \"type\", \"start\", \"include\"");
        System.out.println("If TYPE_OF_FILTER is type: -TARGET_STRINGs are -noun/-verb/-adjective");
        System.out.println("Example: filter words by\\type -verb -noun");
    }

    private static void printHelpListFilterWords() {
        System.out.println("List the words in your filter list.");
        System.out.println("Format: list filter words [limit\\PRINT_LIMIT]");
        System.out.println("Example: list filter words limit\\10");
    }

    private static void printHelpRemind() {
        System.out.println("Remind you of a piece of writing on a specific date.");
        System.out.println("Format: remind DATE");
        System.out.println("Note: DATE should be in the form of dd/MM/yyyy");
        System.out.println("Example: remind 11/11/2020");
    }

    public static void printHelpNoun() {
        System.out.println("Type in the noun you want to save followed by its meaning.");
        System.out.println("Format: noun WORD d\\DESCRIPTION");
        System.out.println("Example: noun cat d\\a feline animal");
    }

    public static void printHelpVerb() {
        System.out.println("Type in the verb you want to save followed by its meaning.");
        System.out.println("Format: verb WORD d\\DESCRIPTION");
        System.out.println("Example: verb eat d\\to consume food");
    }

    public static void printHelpAdj() {
        System.out.println("Type in the adjective you want to save followed by its meaning.");
        System.out.println("Format: adj WORD d\\DESCRIPTION");
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

    /**
     * Prints the list fo filtered bunny ideas.
     * @param numBunny number of bunnies filtered
     * @param filteredBunny list of filtered bunnies and their index.
     */
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

    /**
     * Print a the bunny that was randomly chosen.
     * @param bunnySelected index of the random bunny chosen
     */
    public static void bunnyRandomlySelected(int bunnySelected) {
        System.out.print(FluffleMessages.BUNNY_RANDOMLY_SELECTED_MSG);
        System.out.print(bunniesList.get(bunnySelected).getDescription());
    }

    public static void bunnyListEmpty() {
        System.out.println(FluffleMessages.LIST_BUNNIES_EMPTY_MSG);
    }

    /**
     * Print a single bunny from the list.
     * @param bunny Bunny description
     */
    public static void printBunnyInList(String bunny) {
        System.out.println(bunny);
    }

    public static void printNumBunnies(int numBunny) {
        System.out.println(FluffleMessages.TOTAL_BUNNIES_IN_LIST_MSG + numBunny);
    }

    public static void printClearCommandSuccess(String type, String word) {
        System.out.printf(FluffleMessages.CLEAR_SUCCESS_MESSAGE, type, word);
    }

    public static void echoInput(String input) {
        System.out.println(input);
    }
}
