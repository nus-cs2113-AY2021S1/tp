package seedu.duke.ui;

import static seedu.duke.constants.ClickerMessages.FAREWELL_GREETING;
import static seedu.duke.constants.ClickerMessages.HELLO_GREETING;
import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;

public class UI {
    public static final String NEWLINE = System.lineSeparator();

     /**
     * Line divider set to default before settings are loaded. Default is the first one.
     */
    public static String currentLineDivider = getDivider(1);
    
    // Main Help function
    public static void printHelpMessage(String input) {
        switch(input) {
            case "add":
                printHelpAdd();
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
        String[] listCommands = {"add", "settings", "list", "history", "stats", "review", "quiz", "clear", "exit"};
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
        System.out.println("add t\\<topic> st\\<subtopic> n\\<quiz name> mcq\\<number of MCQ questions> qna\\<number of QNA questions> fb\\ <number of fill in the blanks questions>");
        System.out.println("Example: add t\\Science st\\Chemistry n\\stoichiometry quiz 1 mcq\\3 mrq\\0 qna\\4 fb\\2");
        System.out.println("This adds a quiz of topic 'Science', subtopic 'Chemistry' and named 'stoichiometry quiz 1', with 3 MCQ questions, 0 MRQ questions, 4 QNA questions, and 2 fill in the blank questions.");
    }

    public static void printHelpList() {
        System.out.println("List all available quizzes by typing 'list'.");
    }

    public static void printHelpHistory() {
        System.out.println("Show history and results of quizzes taken in this session by typing 'history'.");
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
        System.out.println("Delete all your saved quizzes in CLIcker by typing 'clear'. A warning prompt will be generated before deletion.");
    }

    public static void printHelpExit() {
        System.out.println("Exit the program by typing 'exit'.");
    }

    public static String getDivider(int input) {
        String divider;
        if(input == 1) {
            divider = "‿︵‿︵‿︵‿︵︵‿︵‿︵‿︵‿︵‿︵‿︵︵‿︵‿︵‿︵‿︵‿︵";
        } else if (input == 2) {
            divider = "---------------------------------------------";
        } else if (input == 3) {
            divider = "=^..^=   =^..^=   =^..^=    =^..^=    =^..^=";
        } else {
            divider = "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-";
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
        System.out.print(String.format(HELLO_GREETING, username));
        printDivider();
    }

    /**
     * Prints the personalised farewell message.
     *
     * @param username user indicated name
     */
    public static void printFarewellMessage(String username) {
        System.out.printf(FAREWELL_GREETING, username);
    }
    
    /**
     * Prints the line divider to the console.
     */
    public static void printDivider() {
        System.out.println(currentLineDivider);
    }
}
