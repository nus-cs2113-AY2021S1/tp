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
        case "add":
            printHelpAdd();
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
        case "review":
            printHelpReview();
            break;
        case "quiz":
            printHelpQuiz();
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
        String[] listCommands = {"add", "settings", "list", "stats", "review", "start", "clear", "exit"};
        System.out.println("Type 'help c\\<function name here>' to view help for each command.");
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

    public static void printHelpAdd() {
        System.out.println("Use the following format for the 'help' command:");
        System.out.println("add t\\<topic> st\\<subtopic> n\\<quiz name> mcq\\<number of MCQ questions> qna\\"
                + "<number of QNA questions> fb\\ <number of fill in the blanks questions>");
        System.out.println("Example: add t\\Science st\\Chemistry n\\stoichiometry quiz 1 mcq\\3 mrq\\0 qna\\4 fb\\2");
        System.out.println("This adds a quiz of topic 'Science', subtopic 'Chemistry' and named 'stoichiometry quiz 1'"
                + ", with 3 MCQ questions, 0 MRQ questions, 4 QNA questions, and 2 fill in the blank questions.");
    }

    public static void printHelpList() {
        System.out.println("List all available quizzes by typing 'list'.");
    }

    public static void printHelpStart() {
        System.out.println("Embark your writings session!");
    }

    public static void printHelpStats() {
        System.out.println("Show your results and statistics of a specific quiz by typing 'quiz t\\QUIZ_INDEX'.");
        System.out.println("Example: quiz t\\3");
    }

    public static void printHelpQuiz() {
        System.out.println("Start a quiz by typing 'quiz t\\QUIZ_INDEX");
        System.out.println("Example: quiz t\\2 starts the quiz with QUIZ_INDEX 2.");
    }

    public static void printHelpReview() {
        System.out.println("Show the answers for the selected quiz by typing 'review t\\QUIZ_INDEX'.");
        System.out.println("You can find the list of quizzes by QUIZ_INDEX by using the list command.");
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

    public static void bunnyDeleted(int bunnyNum) {
        System.out.print(FluffleMessages.BUNNY_DELETED_MSG);
        System.out.print(bunniesList.get(bunnyNum - 1).getDescription());
    }

    public static void printAskForName(String username) {
        System.out.print(FluffleMessages.ASK_FOR_NAME);
    }

    public static void bunnyIndexOutOfBounds() {
        System.out.println("Bunny index is not in list.");
    }

    public static void DividerCommandWrongFormat() {
        System.out.println("Divider command of wrong format");
    }

    public static void DividerIndexOutOfBounds() {
        System.out.println("Divider index indicated out of bounds");
    }
}
