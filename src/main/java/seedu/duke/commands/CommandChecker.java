package seedu.duke.commands;

import java.util.Scanner;

public enum CommandChecker {
    HELP,
    USERNAME,
    DIVIDER,

    NOUN,
    VERB,
    ADJ,
    LIST_WORDS,
    FILTER_WORDS,

    NAME,
    GEN_NAME,
    LIST_NAMES,
    FILTER_NAMES,

    BUNNY,
    LIST_BUNNY,
    FILTER_BUNNY,

    STATS,
    DELETE,
    EXIT,
    UNRECOGNISED;

    public static CommandChecker commandChecker;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getUserInput() {
        System.out.print("You: ");
        String inputLine = SCANNER.nextLine();

        while (inputLine.trim().isEmpty()) {
            System.out.println("Oops! Command cannot be empty");
            inputLine = SCANNER.nextLine();
        }

        commandChecker = extractCommandType(inputLine.trim());

        return inputLine.trim();
    }

    /**
     *  Figure out the command type from userInput.
     *
     * @param userInput string containing user input command
     * @return the type of command detected
     */
    public static CommandChecker extractCommandType(String userInput) {
        if (userInput.toLowerCase().trim().startsWith("help")) {
            return CommandChecker.HELP;
        } else if (userInput.toLowerCase().trim().startsWith("username")) {
            return CommandChecker.USERNAME;
        } else if (userInput.toLowerCase().trim().startsWith("divider")) {
            return CommandChecker.DIVIDER;

        } else if (userInput.toLowerCase().trim().startsWith("noun")) {
            return CommandChecker.NOUN;
        } else if (userInput.toLowerCase().trim().startsWith("verb")) {
            return CommandChecker.VERB;
        } else if (userInput.toLowerCase().trim().startsWith("adj")) {
            return CommandChecker.ADJ;
        } else if (userInput.toLowerCase().trim().startsWith("list words")) {
            return CommandChecker.LIST_WORDS;

        } else if (userInput.toLowerCase().trim().startsWith("bunny")) {
            return CommandChecker.BUNNY;
        } else if (userInput.toLowerCase().trim().startsWith("list bunny")) {
            return CommandChecker.LIST_BUNNY;

        } else if (userInput.toLowerCase().trim().startsWith("exit")) {
            return CommandChecker.EXIT;
        } else {
            return CommandChecker.UNRECOGNISED;
        }
    }

}
